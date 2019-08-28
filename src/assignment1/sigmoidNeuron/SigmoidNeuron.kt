package assignment1.sigmoidNeuron

import kotlin.math.exp

class SigmoidNeuron(
    private val weights: Array<Double>,
    private var bias: Double,
    private val learningRate: Double
    ) {

    fun feed(inputs: List<Double>): Double {
        var epsilon = 0.0
        for (i: Int in inputs.indices) {
            epsilon += weights[i] * inputs[i]
        }

        // output: 1 / (1 + E**( - epsilon - bias) )
        return 1 / ( 1 + exp( -(epsilon + bias) ) )
    }

    private fun learn(diff: Double, inputs: List<Double>) {
        for (i: Int in weights.indices) {
            weights[i] += learningRate * inputs[i] * diff
        }
        bias += learningRate * diff
    }

    fun train(inputs: List<Double>, desiredOutput: Int) {
        val realOutput: Double = feed(inputs)
        learn(desiredOutput - realOutput, inputs)
    }
}