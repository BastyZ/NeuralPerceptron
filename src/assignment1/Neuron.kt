package assignment1

import assignment1.activationFun.Sigmoid
import kotlin.random.Random.Default.nextDouble

class Neuron(
    private var weights: MutableList<Double>,
    private val activationFun: IActivationFun,
    private var bias: Double = 1.0,
    private val learningRate: Double = 0.01
    ): INeuron {

    // basic constructor that receives the number of weights and the activation function
    constructor(nOfWeights: Int,
                activationFun: IActivationFun
                ): this(
        MutableList(nOfWeights) { _ -> nextDouble()},
        activationFun
    )

    // fast constructor that only receives the number of weights
    constructor(nOfWeights: Int): this(nOfWeights, Sigmoid())

    // Delta for error back-propagation
    private var delta: Double = 0.0

    override fun feed(inputs: List<Double>): Double {
        assert(weights.size == inputs.size) {
            "Weights and Inputs lengths does not match"
        }
        var acc = 0.0
        for (i in inputs.indices) {
            acc += weights[i] * inputs[i]
        }

        return activationFun.apply(acc + bias)
    }

    /**
     * When back-propagating errors, sets the new delta
     * according to the error and the actual output of this neuron
      */
    internal fun adjustDeltaWith(error: Double, output: Double) {
        delta = error * activationFun.derivative(output)
    }

    override fun train(
        inputs: List<Double>,
        desiredOutput: Double
    ) {
        for ((i, anInput) in inputs.withIndex()) {
            weights[i] += anInput * delta * learningRate
        }
        bias += learningRate * delta
    }
}