package assignment1

import org.junit.jupiter.api.BeforeEach

abstract class AbstractPerceptronTest {
    protected var tolerance: Double = 0.0
    protected lateinit var inputs11: List<Int>
    protected lateinit var inputs10: List<Int>
    protected lateinit var inputs01: List<Int>
    protected lateinit var inputs00: List<Int>
    protected lateinit var pesos: List<Double>

    @BeforeEach
    fun setUp() {
        tolerance = 0.001
        inputs00 = listOf(0,0)
        inputs01 = listOf(0,1)
        inputs10 = listOf(1,0)
        inputs11 = listOf(1,1)
    }
}
