package assignment1

class Neuron(
    private var weights: MutableList<Double>,
    private var bias: Double,
    private val activationFun: IActivationFun,
    private val learningRate: Double = 0.01
    ): INeuron {

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