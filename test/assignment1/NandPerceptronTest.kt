package assignment1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NandPerceptronTest: AbstractPerceptronTest() {

    @Test
    fun computeBias() {
        pesos = arrayOf(-1.0, -1.0)
        val neuron = NandPerceptron(pesos, 0.0 - tolerance)
        assertEquals(1, neuron.compute(inputs00))
        assertEquals(0, neuron.compute(inputs01))
        assertEquals(0, neuron.compute(inputs10))
        assertEquals(0, neuron.compute(inputs11))
    }
}