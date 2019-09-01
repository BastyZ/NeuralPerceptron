package assignment1.NeuralNetwork

import assignment1.activationFun.Sigmoid
import java.util.Collections.shuffle
import kotlin.math.abs

/**
 * Class that represents an Neural Network, defined with i/o
 */
class NeuralNetwork(
                    private val nOfInputs: Int,
                    val nOfOutputs: Int,
                    neuronsPerInternalLayer: List<Int>,
                    private val activationFun: IActivationFun = Sigmoid()
                    ) {
    var successRate: MutableList<Double> = mutableListOf()
    private var layers: MutableList<NeuronLayer>
    private var trainSets = mutableListOf<TrainSample>()
    private var testSets = mutableListOf<TrainSample>()

    init {
        val nOfInternalLayers = neuronsPerInternalLayer.size
        assert(nOfInternalLayers > 0) { "A Network should have al least 1 internal layer" }

        // We define manually the first and last layer (0 and nOfLayers +1)
        // All the others are created normally
        layers = MutableList(1) { NeuronLayer(nOfInputs, null) }
        // Add internal layers
        neuronsPerInternalLayer.withIndex().forEach { (i, nOfNeurons) ->
            layers.add(NeuronLayer(nOfNeurons, layers[i]))
        }
        // add external layer
        layers.add(NeuronLayer(nOfOutputs, layers.last()))

        // Now we proceed to build each layer and assign next layers
        layers.withIndex().forEach { (i, layer) ->
            when (i) {
                layers.lastIndex ->  layer.build(layers[i-1].outputs.size, activationFun)
                0 -> {
                    // oh the first case
                    layer.build(nOfInputs, activationFun)
                    layer.nextLayer = layers[i+1]
                }
                else -> {
                    layer.build(layers[i-1].outputs.size, activationFun)
                    layer.nextLayer = layers[i+1]
                }
            }
        }
    }

    fun feed(inputs: List<Double>): List<Double> {
        layers.first().feed(inputs)
        return layers.last().outputs.toList()
    }

    private fun backPropagateError(desiredOutput: List<Double>) {
        layers.last().backPropagateError(desiredOutput)
    }

    data class TrainSample(val inputs: List<Double>, val answers: List<Double>)

    fun addTrainSample(inputs: List<Double>, answers: List<Double>){
        trainSets.add(TrainSample(inputs, answers))
    }

    fun addTestSample(inputs: List<Double>, answers: List<Double>){
        testSets.add(TrainSample(inputs, answers))
    }

    fun setSamples(train: MutableList<TrainSample>, test: MutableList<TrainSample>) {
        trainSets = train
        testSets = test
    }

    fun trainRounds(rounds: Int) {
        // Recess the success rate metric
        successRate = mutableListOf()
        repeat(rounds) {
            shuffle(trainSets)

            trainSets.forEach { set ->
                train(set.inputs, set.answers)
            }

            var correctGuesses = 0
            testSets.forEach {set ->
                val output = feed(set.inputs)
                if (isCorrect(output, set.answers)) correctGuesses++
            }
            successRate.add(correctGuesses.toDouble()/testSets.size)
            println(successRate.last())
        }
    }

    private fun isCorrect(output: List<Double>, ans: List<Double>): Boolean {
        val correctIndex = ans.indexOf(ans.max())
        val correctCheck: Boolean = abs(output[correctIndex] - ans[correctIndex])  <= .05

        var othersCheck = true
        loop@ for (i in ans.indices) {
            if (i == correctIndex) continue
            else {
                when {
                    abs(output[i] - ans[i])  >= .2 -> continue@loop
                    else -> {
                        othersCheck = false
                        break@loop
                    }
                }
            }
        }

        return correctCheck && othersCheck
    }

    internal fun train(inputs: List<Double>, desiredOutput: List<Double>) {
        feed(inputs)
        backPropagateError(desiredOutput)
        layers.first().updateWeights(inputs)
    }

    companion object Utils {
        var min = 0.0
        var max = 0.0
        // Seed to be used by the Neuron to create the weights
        const val seed: Int = 42

        fun normalize(list: List<Double>): List<Double> {
            val aList = mutableListOf<Double>()
            list.forEach { value ->
                aList.add(normalize(value))
            }
            return aList.toList()
        }

        fun normalize(x: Double): Double {
            return (x - min) / (max - min)
        }

        fun extractMaxAndMin(list: List<Double>) {
            min = list.min()!!
            max = list.max()!!
        }
    }
}
