package assignment1.perceptrons

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AndPerceptronTest: AbstractPerceptronTest() {

    @Test
    fun computeBias() {
        pesos = arrayOf(1.0,1.0)
        val neuron = AndNeuron(pesos, -1.1 - super.tolerance)
        assertEquals(0.0, neuron.feed(inputs00))
        assertEquals(0.0, neuron.feed(inputs01))
        assertEquals(0.0, neuron.feed(inputs10))
        assertEquals(1.0, neuron.feed(inputs11))
    }
}