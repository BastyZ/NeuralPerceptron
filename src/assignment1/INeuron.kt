package assignment1

interface INeuron {

    fun feed(inputs: List<Double>): Double

    fun train(inputs: List<Double>, desiredOutput: Double)

}