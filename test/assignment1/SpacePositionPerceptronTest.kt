package assignment1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class SpacePositionPerceptronTest : AbstractPerceptronTest() {

    private val testingPoints: Int = 10000000
    private val trainingPoints: Int = 100
    private val trainingSessions: Int = 100
    private val width: Int = 20
    private val height: Int = 20
    private val initialWeights: Array<Double> = arrayOf(0.0, 0.0)
    private val initialBias: Double = 0.0
    private val testingSample = createPoints(testingPoints)
    private val trainingSample = createPoints(trainingPoints)

    private fun overLine(x: Int, y: Int): Int {
        // Line y = -x - 4
        return when {
            -x - 4 > y  -> 1
            else        -> 0
        }
    }

    private fun createPoints(nOfPoints: Int): Array<Pair<List<Int>, Int>> {
        // Creates a list of points and the desired output for each one
        return Array(nOfPoints) {
            val x: Int = Random.nextInt(-width,width)
            val y: Int = Random.nextInt(-height,height)
            val desiredOutput: Int = overLine(x, y)
            Pair(listOf(x,y), desiredOutput)
        }
    }

    @Test
    fun trainOnceForAll() {
        val neuron = SpacePositionPerceptron(initialWeights,initialBias)
        trainingSample.forEach {
            neuron.train(it.first, it.second)
        }

        // We change the set of points to test
        var successfulTries = 0
        testingSample.forEach {
            when {
                neuron.compute(it.first) == it.second -> successfulTries++
            }
        }
        val successRate: Double = successfulTries.toDouble() / testingPoints.toDouble()
        print("One train for all: success rate: $successRate \n")
        // We want it to be better than random
        assertTrue(successRate > 0.5)
    }

    @Test
    fun trainNTimesForAll() {
        val neuron = SpacePositionPerceptron(initialWeights,initialBias)
        repeat(trainingSessions) {
            trainingSample.forEach {
                neuron.train(it.first, it.second)
            }
        }
        // We change the set of points to test
        var successfulTries = 0
        testingSample.forEach {
            when {
                neuron.compute(it.first) == it.second -> successfulTries++
            }
        }
        val successRate: Double = successfulTries.toDouble() / testingPoints.toDouble()
        print("N Trainings for all: success rate: $successRate \n")
        assertTrue(successRate > 0.7)
    }

    @Test
    fun noTrainForAll() {
        val neuron = SpacePositionPerceptron(initialWeights,initialBias)

        // We change the set of points to test
        var successfulTries = 0
        testingSample.forEach {
            when {
                neuron.compute(it.first) == it.second -> successfulTries++
            }
        }
        val successRate: Double = successfulTries.toDouble() / testingPoints.toDouble()
        print("no train for all: success rate: $successRate \n")
        assertTrue(successRate < 0.6)
    }

}