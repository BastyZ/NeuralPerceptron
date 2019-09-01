package assignment1

import assignment1.NeuralNetwork.InputHandler.normalize
import assignment1.activationFun.Sigmoid
import assignment1.activationFun.Step
import assignment1.activationFun.Tanh
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class NeuralNetworkTest {

    private val testingPoints: Int = 100000
    private val trainingPoints: Int = 1000
    private val trainingSessions: Int = 10000
    private val width: Int = 20
    private val height: Int = 20
    private val trainOnes = NeuralNetwork.TrainSample(listOf(1.0,1.0), listOf(1.0))

    private val testingSets = createPoints(testingPoints)
    private val trainingSets = createPoints(trainingPoints)

    lateinit var network: NeuralNetwork

    private fun overLine(x: Double, y: Double): Double {
        // Line y = -x
        return when {
            -x > y  -> 1.0
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
        val layers: List<Int> = listOf(8,8,8)
        network = NeuralNetwork(2,1, layers, Sigmoid())
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
        // This test could fail due to randomness

        // On this case we already know the min and max, so we set them
        NeuralNetwork.max = 20.0
        NeuralNetwork.min = -20.0

        trainingSets.forEach { set -> network.addTrainSample(set.inputs, set.answers) }
        network.trainRounds(trainingSessions)
        var success = .0
        var ext = Pair(1.0,0.0)


        testingSets.forEach { set ->
            val output = network.feed(set.inputs)
            ext = Pair(min(ext.first,output.first()), max(ext.second,output.first()))
            if (abs(set.answers.first() - output.first()) < 0.01) {success++}
            else {
                println("isCorrect? :\t false \t\t ${output.first()} \t ${normalize(set.inputs)} \t | ${set.answers}")}
        }
        println("error:: min ${ext.first}  max ${ext.second} \n" +
                "success rate ${success/testingSets.size}")
        assertTrue(success/testingSets.size > .9)
    }

    @Test
    fun companionTest() {
        val aList = listOf<Double>(-20.0,5.0,6.0,-2.5)
        NeuralNetwork.extractMaxAndMin(aList)
        assertEquals(NeuralNetwork.max, 6.0)
        assertEquals(NeuralNetwork.min, -20.0)

        assertEquals(normalize(aList[1]), 25.0/26.0)
        assertEquals(normalize(aList[2]), 1.0)
        assertEquals(normalize(aList.last()), 17.5/26.0)
    }
}