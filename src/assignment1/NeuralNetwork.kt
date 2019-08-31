package assignment1

import assignment1.activationFun.Sigmoid
import java.util.Collections.shuffle

/**
 * Class that represents an Neural Network, defined with i/o
 */
class NeuralNetwork(
                    private val nOfInputs: Int,
                    private val nOfOutputs: Int,
                    private val neuronsPerInternalLayer: List<Int>,
                    private val activationFun: IActivationFun = Sigmoid()
                    ) {

    var successRate: MutableList<Double> = mutableListOf()
    private lateinit var layers: MutableList<NeuronLayer>
    private var trainSets = mutableListOf<TrainSet>()

    init {
        val nOfInternalLayers = neuronsPerInternalLayer.size
        assert(nOfInternalLayers > 0) { "A Network should have al least 1 internal layer" }

        // We define manually the first and last layer (0 and nOfLayers +1)
        // All the others are created normally
        layers = MutableList(nOfInternalLayers + 2) {
            when (it) {
                0               -> NeuronLayer(nOfInputs, null)
                layers.lastIndex-> NeuronLayer(nOfOutputs, layers[it])
                else            -> NeuronLayer(neuronsPerInternalLayer[it+1], layers[it])
            }
        }

        // Now we proceed to build each layer and assign next layers
        layers.withIndex().forEach { (i, layer) ->
            when (i) {
                layers.lastIndex ->  layer.build(nOfOutputs, activationFun)
                0 -> {
                    // oh the first case
                    layer.build(nOfInputs, activationFun)
                    layer.nextLayer = layers[i+1]
                }
                else -> {
                    layer.build(neuronsPerInternalLayer[i-1], activationFun)
                    layer.nextLayer = layers[i+1]
                }
            }
        }
    }

    fun feed(inputs: List<Double>): MutableList<Double> {
        layers.first().feed(inputs)
        return layers.last().outputs
    }

    private fun backPropagateError(desiredOutput: List<Double>) {
        layers.last().backPropagateError(desiredOutput)
    }

    internal data class TrainSet(val inputs: List<Double>, val answers: List<Double>)

    fun addTrainSet(inputs: List<Double>, answers: List<Double>){
        trainSets.add(TrainSet(inputs, answers))
    }

    fun trainRounds(rounds: Int) {
        // Recess the success rate metric
        successRate = mutableListOf()
        repeat(rounds) {
            var correctGuesses = 0
            shuffle(trainSets)

            trainSets.forEach { set ->
                train(set.inputs, set.answers)
                when { layers.last().outputs == set.answers -> correctGuesses++ }
            }
            successRate.add(correctGuesses.toDouble() / trainSets.size * 100)
        }
    }

    internal fun train(inputs: List<Double>, desiredOutput: List<Double>) {
        feed(inputs)
        backPropagateError(desiredOutput)
        layers.last().updateWeights(inputs)
    }
}
