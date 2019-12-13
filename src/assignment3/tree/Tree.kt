package assignment3.tree

import assignment3.nodes.Node
import kotlin.random.Random.Default.nextInt

class Tree(
    private val chromosomeGenerator: Ast,
    var fitnessFun: (Node) -> Double,
    var depth: Int,
    val filterFun: ((Tree) -> Boolean)? = null // TODO use when there's forbidden combinations
    ): Comparable<Tree> {
    /**
     * This is the "equivalent" of a Being on assignment 2, but we'll change the analogy to forest and trees
     */

    var root: Node = chromosomeGenerator.invoke(depth)
    var nodes: MutableList<Node> = mutableListOf()
    var fitness: Double = Double.MAX_VALUE // the closer to zero the closer to the target value

    init {
        nodes = root.serialize()
    }

    fun crossover(otherTree: Tree): Tree {
        /** Generates an offspring from 2 parents. */
        // TODO cross-over
        return this
    }


    fun mutate() {
        /** Mutates the tree chromosome using a new sub-tree as a base. */
        val nodeToMutate: Node = nodes.random()
        val newSubTree: Node = Tree(
            chromosomeGenerator,
            fitnessFun,
            nextInt(nodeToMutate.depth)
            ).root

        nodeToMutate.replace(newSubTree)
        nodes = root.serialize() // updates the serialization of this tree
    }

    private fun updateFitness() {
        fitness = fitnessFun(this.root)
    }

    override fun compareTo(other: Tree): Int {
        return when {
            fitness == other.fitness -> 0
            fitness > other.fitness  -> 1
            else  -> -1
        }
    }
}