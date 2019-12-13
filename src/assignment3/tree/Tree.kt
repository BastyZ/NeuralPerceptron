package assignment3.tree

import assignment3.nodes.Node
import kotlin.random.Random.Default.nextDouble

class Tree(
    private val chromosomeGenerator: Ast,
    var fitnessFun: (Node) -> Int,
    val depth: Int,
    baseRoot: Node?
    ): Comparable<Tree> {
    /**
     * This is the "equivalent" of a Being on assignment 2, but we'll change the analogy to forest and trees
     */

    internal var root: Node = when (baseRoot) {
        null -> chromosomeGenerator.invoke(depth)
        else -> baseRoot
    }
    var nodes: MutableList<Node> = mutableListOf()
    var fitness: Int = Int.MIN_VALUE

    init {
        nodes = root.serialize()
        this.eval() // this is to speed the process on testing (it can be donde by hand too)
    }

    /** Constructor with no pre-defined root */
    constructor(
        chromosomeGenerator: Ast,
        fitnessFun: (Node) -> Int,
        depth: Int
    ) : this(chromosomeGenerator, fitnessFun, depth, baseRoot = null)

    fun crossover(other: Tree): Tree {
        /** Generates an offspring from 2 parents. */
        val chromosomeSample: Node = this.root.copy()
        // we pick a random node to do the crossover
        val mixingPoint: Node = chromosomeSample.serialize().random()
        var newSubTree: Node?

        // we pick a subtree that is less deep than the remaining depth (do..while loop)
        do newSubTree = other.nodes.random()
        while (newSubTree!!.depth > mixingPoint.depth)

        // we mix and deliver
        mixingPoint.replace(newSubTree)
        return Tree(chromosomeGenerator, fitnessFun, depth, chromosomeSample)
    }

    fun mutate(mutationRate: Double) {
        /** Mutates the tree chromosome using a new sub-tree as a base. */
        if (nextDouble() < mutationRate) {
            val nodeToMutate: Node = nodes.random()
            val newSubTree: Node = Tree(
                chromosomeGenerator,
                fitnessFun,
                (0..nodeToMutate.depth).random() // random depth between the node one, and zero
            ).root

            // replace and update our serialization
            nodeToMutate.replace(newSubTree)
            nodes = root.serialize()
        }
    }

    fun eval(): Int {
        updateFitness()
        return root.eval()
    }

    private fun updateFitness() {
        fitness = fitnessFun(this.root)
    }

    override fun toString(): String {
        return root.toString()
    }

    override fun compareTo(other: Tree): Int {
        return when {
            fitness == other.fitness -> 0
            fitness > other.fitness  -> 1
            else  -> -1
        }
    }
}