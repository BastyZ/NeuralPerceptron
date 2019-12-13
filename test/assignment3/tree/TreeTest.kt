package assignment3.tree

import assignment3.addFun
import assignment3.multFun
import assignment3.nodes.Node
import assignment3.subFun
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TreeTest {

    lateinit var tree:Tree

    @BeforeEach
    fun setUp() {
        val functions: MutableList<(Node, Node) -> Int> = mutableListOf(
            addFun(),
            subFun(),
            multFun()
        )
        val values: MutableList<Int> = mutableListOf(1,2,4,6,8)
        val chromosomeGenerator: Ast = Ast(functions, values)

        // we are going to pass an anonymous function for testing purposes
        tree = Tree(chromosomeGenerator, fun(a: Node) = a.eval().toDouble(), 4)
    }

    @Test
    fun build() {
        println("Generated tree: $tree")
        println("value: ${tree.eval()} | fitness: ${tree.fitness}")
        println("Description (depth: ${tree.root.depth}): ${tree}")
        assertNotNull(tree.root, "Tree is null")
        assertEquals(tree.depth, tree.root.depth, "Depth does not match")
    }

}