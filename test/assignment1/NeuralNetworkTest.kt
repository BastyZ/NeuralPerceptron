package assignment1

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class NeuralNetworkTest {

    private val testingPoints: Int = 10000000
    private val trainingPoints: Int = 100
    private val trainingSessions: Int = 100
    private val width: Int = 20
    private val height: Int = 20
    private val trainOnes = NeuralNetwork.TrainSample(listOf(1.0,1.0), listOf(1.0))

    private val testingSets = createPoints(testingPoints)
    private val trainingSets = createPoints(trainingPoints)

    lateinit var network: NeuralNetwork

    private fun overLine(x: Double, y: Double): Double {
        // Line y = -x - 4
        return when {
            -x - 4.0 > y  -> 1.0
            else        -> 0.0
        }
    }

    private fun createPoints(nOfPoints: Int): Array<NeuralNetwork.TrainSample> {
        // Creates a list of points and the desired output for each one
        return Array(nOfPoints) {
            val x: Double = Random.nextInt(-width,width).toDouble()
            val y: Double = Random.nextInt(-height,height).toDouble()
            NeuralNetwork.TrainSample(listOf(x,y), listOf(overLine(x,y)))
        }
    }

    @BeforeEach
    fun setUp() {
        val layers: List<Int> = listOf(3,5,3)
        network = NeuralNetwork(2,1, layers)
    }

    @Test
    fun feed() {
        val output = network.feed(trainOnes.inputs)
        assertTrue(!output.first().isNaN())
        assertEquals(output.size, network.nOfOutputs)
        println("feed :: output is $output")
    }

    @Test
    fun trainRounds() {
        testingSets.forEach { set -> network.addTrainSample(set.inputs, set.answers) }
        network.trainRounds(trainingSessions)
        print(network.successRate)
    }
}