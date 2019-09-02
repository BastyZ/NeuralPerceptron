package assignment1.NeuralNetwork

import assignment1.activationFun.Sigmoid
import kotlin.math.pow

class NeuronLayer(private val nOfNeurons: Int , private var previousLayer: NeuronLayer?) {
    private lateinit var neurons: Array<Neuron>
    internal val outputs = MutableList(nOfNeurons) {.0}
    internal var nextLayer: NeuronLayer? = null

    /**
     * Builds the Neurons on the layer with the given wights number and activation fun
     *
     * @param nOfWeights quantity of weights of each neuron
     * @param activationFun activation function that each neuron on the layer uses
     */
    internal fun build(nOfWeights: Int, activationFun: IActivationFun = Sigmoid()) {
        neurons = Array(nOfNeurons) { Neuron(nOfWeights, activationFun) }
    }

    /**
     * Gives an input to a neuron layer, and passes the output to the next if exists
     *
     * @param inputs values to give to each Neuron
     */
    internal fun feed(inputs: List<Double>): MutableList<Double> {
        assert(::neurons.isInitialized) { "Layer must be built first" }
        neurons.withIndex().forEach {(i, neuron) ->
            outputs[i] = neuron.feed(inputs)
        }
        // feed the next layer or return the output of this layer
        return when {
            nextLayer != null   -> nextLayer!!.feed(outputs)
            else                -> outputs
        }
    }

    /**
     * Adjusts the parameters of the layer by back-propagation
     * (this is a recursive method)
     *
     * @param desiredOutput list of desired outputs for a given input list
     */
    internal fun backPropagateError(desiredOutput: List<Double>) {
        assert(::neurons.isInitialized) { "Layer must be built first" }
        // calculates the error for each individual neuron
        for ((i, neuron) in neurons.withIndex()) {
            val error = (desiredOutput[i] - outputs[i]).pow(2)
            neuron.adjustDeltaWith(error, outputs[i])
        }
        // if there is a previous layer, we keep propagating the error
        previousLayer?.backPropagateError()
    }

    /**
     * Adjusts the parameters of the layer by back-propagation
     * (this is a recursive method)
     */
    private fun backPropagateError() {
        for ((i, neuron) in neurons.withIndex()) {
            var error = .0
            // We retrieve the error associated to the output value we gave to the next layer's neurons
            for (nextLayerNeuron in nextLayer!!.neurons) {
                error += nextLayerNeuron.errorForWeight(i)
            }
            // We adjust this neuron according to that error and this neuron's output
            neuron.adjustDeltaWith(error, outputs[i])
        }
        previousLayer?.backPropagateError()
    }

    internal fun updateWeights(networkInputs: List<Double>) {
        // chooses the source for the inputs
        val inputs: List<Double> = when (previousLayer) {
            null -> networkInputs
            else -> previousLayer!!.outputs
        }
        // adjust each individual Neuron's weights
        neurons.forEach { neuron ->
            neuron.train(inputs)
        }
        // triggers an update on the next layer if it exists
        nextLayer?.updateWeights(networkInputs)
    }

}