package assignment1

import assignment1.activationFun.Sigmoid
import assignment1.activationFun.Step
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class NeuronTest {
    private val testingPoints: Int = 10000
    private val trainingPoints: Int = 100
    private val trainingSessions: Int = 50
    private val width: Int = 20
    private val height: Int = 20
    private val initialWeights = mutableListOf<Double>(1.0, 1.0)
    private val initialBias: Double = 0.0
    private val testingSample = createPoints(testingPoints)
    private val trainingSample = createPoints(trainingPoints)
    private val inputs00 = listOf(0.0,0.0)
    private val inputs01 = listOf(0.0,1.0)
    private val inputs10 = listOf(1.0,0.0)
    private val inputs11 = listOf(1.0,1.0)

    lateinit var neuron: Neuron

    private fun overLine(x: Double, y: Double): Double {
        // Line y = -x - 4
        return when {
            -x - 4.0 > y  -> 1.0
            else        -> 0.0
        }
    }

    private fun createPoints(nOfPoints: Int): Array<Pair<List<Double>, Double>> {
        // Creates a list of points and the desired output for each one
        return Array(nOfPoints) {
            val x: Double = Random.nextInt(-width,width).toDouble()
            val y: Double = Random.nextInt(-height,height).toDouble()
            val desiredOutput: Double = overLine(x, y)
            Pair(listOf(x,y), desiredOutput)
        }
    }

    @BeforeEach
    fun setUp() {
        // If nothing is made we start the test with a neuron
        // with bias 0.0 and weights 1.0 and Sigmoid activation fun
        neuron = Neuron(initialWeights, initialBias, Sigmoid())
    }

    @Test
    fun andStep() {
        neuron = Neuron(initialWeights, -1.1, Step())
        assertEquals(0.0, neuron.feed(inputs00))
        assertEquals(0.0, neuron.feed(inputs01))
        assertEquals(0.0, neuron.feed(inputs10))
        assertEquals(1.0, neuron.feed(inputs11))
    }

    @Test
    fun nandStep() {
        neuron = Neuron(mutableListOf(-1.0, -1.0), .9, Step())
        assertEquals(1.0, neuron.feed(inputs00))
        assertEquals(0.0, neuron.feed(inputs01))
        assertEquals(0.0, neuron.feed(inputs10))
        assertEquals(0.0, neuron.feed(inputs11))
    }

    @Test
    fun orStep() {
        neuron = Neuron(initialWeights, -.1, Step())
        assertEquals(0.0, neuron.feed(inputs00))
        assertEquals(1.0, neuron.feed(inputs01))
        assertEquals(1.0, neuron.feed(inputs10))
        assertEquals(1.0, neuron.feed(inputs11))
    }

    @Test
    fun train() {
    }
}