package assignment1

import assignment1.activationFun.Step
import com.sun.org.apache.xerces.internal.impl.xpath.XPath
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import kotlin.test.assert

internal class NeuronLayerTest {

    private val outLayerCount = 1
    private val neuronCount: Int = 10
    var layer: NeuronLayer = NeuronLayer(neuronCount, null)
    var outerLayer = NeuronLayer(outLayerCount, layer)

    @BeforeEach
    fun setUp() {
        layer.build(2, Step())
        layer.nextLayer = outerLayer
        outerLayer.build(neuronCount, Step())
    }

    @Test
    fun feed() {
        val output = layer.feed(listOf(1.0,1.0))
        // We'll check that is exactly one result, and it's of the class we want it
        assert(output.size == 1)
        assert(output.first()::class == Double::class)
    }
}