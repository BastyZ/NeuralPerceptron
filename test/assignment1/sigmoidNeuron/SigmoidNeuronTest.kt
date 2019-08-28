package assignment1.sigmoidNeuron

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class SigmoidNeuronTest {
    protected var tolerance: Double = 0.0
    protected lateinit var inputs11: List<Double>
    protected lateinit var inputs10: List<Double>
    protected lateinit var inputs01: List<Double>
    protected lateinit var inputs00: List<Double>
    protected lateinit var pesos: Array<Double>

    @BeforeEach
    fun setUp() {
        tolerance = 0.001
        inputs00 = listOf(0.0,0.0)
        inputs01 = listOf(0.0,1.0)
        inputs10 = listOf(1.0,0.0)
        inputs11 = listOf(1.0,1.0)
    }

    @Test
    fun computeWithOutTrain() {
        val neuron = SigmoidNeuron(arrayOf(2.0,2.0),1.0, tolerance)
        assertTrue(neuron.feed(inputs00) > 0.0)
    }

    @Test
    fun computeAnd() {
        val neuron = SigmoidNeuron(arrayOf(2.0, 2.0), .0, tolerance)
        assertTrue(neuron.feed(inputs00) < .6)
    }

    @Test
    fun neuronCurveTest() {
        val neuron = SigmoidNeuron(arrayOf(1.0),.0, tolerance)
        var lastValue = 1.0
        for (j in 20 downTo -20 step 2) {
            val i = j.toDouble() / 10
            // testing that is the curve it's supposed to be
            assertTrue(lastValue - neuron.feed(listOf(i)) <= 0.15 )
            assertTrue(neuron.feed(listOf(i)) > .0)
            lastValue = neuron.feed(listOf(i))
        }
    }
}