package assignment1

import assignment1.activationFun.Sigmoid
import assignment1.activationFun.Step
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.abs
import kotlin.random.Random

internal class NeuronTest {
    private val testingPoints: Int = 10000
    private val trainingPoints: Int = 10000
    private val trainingSessions: Int = 1000
    private val width: Int = 20
    private val height: Int = 20
    private val initialWeights = mutableListOf<Double>(1.0, 1.0)
    private val initialBias: Double = 0.0
    private val testingSample = createPoints(testingPoints)
    private val trainingSample = createPoints(trainingPoints)
    private val inputs00 = listOf(0.0, 0.0)
    private val inputs01 = listOf(0.0, 1.0)
    private val inputs10 = listOf(1.0, 0.0)
    private val inputs11 = listOf(1.0, 1.0)

    lateinit var neuron: Neuron

    private fun overLine(x: Double, y: Double): Double {
        // Line y = -x - 4
        return when {
            -x - 4.0 > y -> 1.0
            else -> 0.0
        }
    }

    private fun createPoints(nOfPoints: Int): Array<Pair<List<Double>, Double>> {
        // Creates a list of points and the desired output for each one
        return Array(nOfPoints) {
            val x: Double = Random.nextInt(-width, width).toDouble()
            val y: Double = Random.nextInt(-height, height).toDouble()
            val desiredOutput: Double = overLine(x, y)
            Pair(listOf(x, y), desiredOutput)
        }
    }

    @BeforeEach
    fun setUp() {
        // If nothing is made we start the test with a neuron
        // with bias 0.0 and weights 1.0 and Sigmoid activation fun
        neuron = Neuron(initialWeights, Sigmoid(), initialBias, 0.1)
    }

    @Test
    fun andStep() {
        neuron = Neuron(initialWeights, Step(), -1.1)
        assertEquals(0.0, neuron.feed(inputs00))
        assertEquals(0.0, neuron.feed(inputs01))
        assertEquals(0.0, neuron.feed(inputs10))
        assertEquals(1.0, neuron.feed(inputs11))
    }

    @Test
    fun nandStep() {
        neuron = Neuron(mutableListOf(-1.0, -1.0), Step(), .9)
        assertEquals(1.0, neuron.feed(inputs00))
        assertEquals(0.0, neuron.feed(inputs01))
        assertEquals(0.0, neuron.feed(inputs10))
        assertEquals(0.0, neuron.feed(inputs11))
    }

    @Test
    fun orStep() {
        neuron = Neuron(initialWeights, Step(), -.1)
        assertEquals(0.0, neuron.feed(inputs00))
        assertEquals(1.0, neuron.feed(inputs01))
        assertEquals(1.0, neuron.feed(inputs10))
        assertEquals(1.0, neuron.feed(inputs11))
    }

    @Test
    fun spaceSigmoidNoTrains() {
        var successfulTries = 0
        // predicts for all the training sample
        testingSample.forEach {
            when {
                abs( it.second - neuron.feed(it.first) ) < .05 -> successfulTries++
            }
        }
        val successRate: Double = successfulTries.toDouble() / testingPoints
        println("No training :: SuccessRate is $successRate")
        // with no training we expect to perform almost like random
        assertTrue(successRate < .6)
    }

    @Test
    fun spaceSigmoidTrainOnce() {
        // train the neuron with the training sample
        // error is not computed using derivatives (yet, we'll use it on the network)
        trainingSample.forEach {
            val output = neuron.feed(it.first)
            neuron.adjustDeltaWith(it.second - output, output)
            neuron.train(it.first)
        }

        // we use another sample on the same line to test the result
        var successfulTries = 0
        testingSample.forEach {
            when {
                abs(it.second - neuron.feed(it.first)) < .05 -> successfulTries++
            }
        }
        val successRate: Double = successfulTries.toDouble() / testingPoints
        println("One training :: SuccessRate is $successRate")
        // we expect this to be better than random
        assert(successRate > .5)
    }

    @Test
    fun spaceSigmoidTrainN(){
        // train the neuron with the training sample various times
        // error is not computed using derivatives (yet, we'll use it on the network)
        repeat(trainingSessions) {
            trainingSample.forEach {
                val output = neuron.feed(it.first)
                neuron.adjustDeltaWith(it.second - output, output)
                neuron.train(it.first)
            }
        }

        // we use another sample on the same line to test the result
        var successfulTries = 0
        testingSample.forEach {
            when {
                abs( it.second - neuron.feed(it.first) ) < .05 -> successfulTries++
            }
        }
        val successRate: Double = successfulTries.toDouble() / testingSample.size
        print("$trainingSessions Trainings :: SuccessRate is $successRate \n")
        // We expect it to perform very well with various rounds of training
        assertTrue(successRate > 0.8)
    }

    @Test
    fun secondaryConstructor() {
        neuron = Neuron(2)
        var successfulTries = 0
        // predicts for all the training sample
        testingSample.forEach {
            when {
                abs( it.second - neuron.feed(it.first) ) < .05 -> successfulTries++
            }
        }
        val successRate: Double = successfulTries.toDouble() / testingPoints
        println("No training on Random Neuron :: SuccessRate is $successRate")
        // with no training we expect to perform almost like random
        assertTrue(successRate < .6)
    }
}