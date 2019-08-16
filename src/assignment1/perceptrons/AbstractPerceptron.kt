package assignment1.perceptrons

abstract class AbstractPerceptron(
    private val weights: Array<Double>,
    private var bias: Double,
    private val learningRate: Double
) {

    fun compute(inputs: List<Int>): Int {
        var output = 0.0
        for (i: Int in inputs.indices) {
            output += weights[i] * inputs[i]
        }
        return when {
            output > bias      -> 1
            else               -> 0
        }
    }

    private fun learn(diff: Int, inputs: List<Int>) {
        for (i in weights.indices) {
            weights[i] += learningRate * inputs[i] * diff
        }
        bias += learningRate * diff
    }

    fun train(inputs: List<Int>, desiredOutput: Int) {
        val realOutput: Int = compute(inputs)
        learn(desiredOutput - realOutput, inputs)
    }

}