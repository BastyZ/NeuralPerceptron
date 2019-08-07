package assignment1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AndPerceptronTest: AbstractPerceptronTest() {

    @Test
    fun computeBias() {
        pesos = listOf(1.0,1.0)
        val neuron = AndPerceptron(pesos,2.0 - super.tolerance)
        assertEquals(0, neuron.compute(inputs00))
        assertEquals(0, neuron.compute(inputs01))
        assertEquals(0, neuron.compute(inputs10))
        assertEquals(1, neuron.compute(inputs11))
    }
}