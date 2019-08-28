package assignment1

class Neuron(
    private var weights: MutableList<Double>,
    private var bias: Double,
    private val activationFun: IActivationFun,
    private val learningRate: Double = 0.01
    ): INeuron {

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

    override fun train(inputs: List<Double>, desiredOutput: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}