package assignment1.perceptrons

abstract class AbstractPerceptron(
    private val weights: Array<Double>,
    private var bias: Double,
    private val learningRate: Double
) {

    fun compute(inputs: List<Double>): Double {
        var output = 0.0
        for (i: Int in inputs.indices) {
            output += weights[i] * inputs[i]
        }
        return when {
            output > bias      -> 1.0
            else               -> 0.0
        }
    }

    private fun learn(diff: Double, inputs: List<Double>) {
        for (i in weights.indices) {
            weights[i] += learningRate * inputs[i] * diff
        }
        bias += learningRate * diff
    }

    fun train(inputs: List<Double>, desiredOutput: Double) {
        val realOutput: Double = compute(inputs)
        learn(desiredOutput - realOutput, inputs)
    }

}