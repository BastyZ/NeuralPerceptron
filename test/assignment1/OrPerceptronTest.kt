package assignment1

import assignment1.perceptrons.OrNeuron
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OrPerceptronTest : AbstractPerceptronTest() {

    @Test
    fun computeBias() {
        pesos = arrayOf(1.0, 1.0)
        val neuron = OrNeuron(pesos, - tolerance)
        assertEquals(0.0, neuron.feed(inputs00))
        assertEquals(1.0, neuron.feed(inputs01))
        assertEquals(1.0, neuron.feed(inputs10))
        assertEquals(1.0, neuron.feed(inputs11))
    }
}