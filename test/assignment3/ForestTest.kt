package assignment3

import assignment3.nodes.Node
import assignment3.tree.Ast
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.math.abs

internal class ForestTest {

    lateinit var forest: Forest
    private val population = 20

    @BeforeEach
    fun setUp() {
        val functions: MutableList<(Node, Node) -> Int> = mutableListOf(
            addFun(),
            subFun(),
            multFun()
        )
        val values: MutableList<Int> = mutableListOf(1,2,4)
        val chromosomeGenerator: Ast = Ast(functions, values)

        // we are going to pass an anonymous function for testing purposes
        forest = Forest(
            population,
            chromosomeGenerator,
            fun(a: Node) = (Int.MIN_VALUE + abs(42 - a.eval())).toDouble(), // distance to 42
            4)
    }

    @Test
    fun build() {
        println("-------------------------------Build Test-------------------------------")
        assertNotNull(forest, "Forest is null")
        assertEquals(population, forest.size, "Something weir, forest size does not match")
        println("Forest of size: ${forest.size}")
        println("Fittest: ${forest.getFittest()} fitness: ${forest.getFittest().fitness}")
        println("------------------------------------------------------------------------")
    }

    @Test
    fun evolve() {
        println("-------------------------------Evolve Test-------------------------------")
        assertNotNull(forest, "Forest is null")
        assertNotNull(forest.getFittest(), "Fittest is null")
        val fitness1 = forest.fittestFitness()
        forest.evolve()
        println("We'll see if this forest improves (old fitness: $fitness1)")
        println("new fitness: ${forest.fittestFitness()}")
        println("------------------------------------------------------------------------")
    }
}