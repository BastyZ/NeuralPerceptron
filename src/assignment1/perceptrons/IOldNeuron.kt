package assignment1.perceptrons

interface IOldNeuron {

    /**
     * Gives a prediction for a given input based on its weights and bias
     */
    fun feed(inputs: List<Double>): Double

    /**
     * Allows the neuron to learn, based on error and the desired prediction for that
     * particular input
     */
    fun train(
        inputs: List<Double>,
        desiredOutput: Double
    )

}