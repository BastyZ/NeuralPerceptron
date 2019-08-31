package assignment1

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class NeuralNetworkTest {
    lateinit var network: NeuralNetwork
    val trainOnes = NeuralNetwork.TrainSet(listOf(1.0,1.0), listOf(1.0))

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

    }
}