package assignment1

import assignment1.perceptrons.OrNeuron
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OrPerceptronTest : AbstractPerceptronTest() {

    @Test
    fun computeBias() {
        pesos = arrayOf(1.0, 1.0)
        val neuron = OrNeuron(pesos, pesos.min()!! - tolerance)
        assertEquals(0.0, neuron.compute(inputs00))
        assertEquals(1.0, neuron.compute(inputs01))
        assertEquals(1.0, neuron.compute(inputs10))
        assertEquals(1.0, neuron.compute(inputs11))
    }
}