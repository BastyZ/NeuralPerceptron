package assignment3.tree

import assignment3.nodes.Node
import kotlin.random.Random.Default.nextInt

class Tree(
    private val chromosomeGenerator: Ast,
    var fitnessFun: (Node) -> Double,
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
    var fitness: Double = Double.MAX_VALUE // the closer to zero the closer to the target value

    init {
        this.eval() // this is to speed the process on testing (it can be donde by hand too)
        nodes = root.serialize()
    }

    /** Constructor with no pre-defined root */
    constructor(
        chromosomeGenerator: Ast,
        fitnessFun: (Node) -> Double,
        depth: Int
    ) : this(chromosomeGenerator, fitnessFun, depth, baseRoot = null)

    fun crossover(other: Tree): Tree {
        /** Generates an offspring from 2 parents. */
        val chromosomeSample: Node = this.root.copy()
        // we pick a random node to do the crossover
        val mixingPoint: Node = chromosomeSample.serialize().random()
        var newSubTree: Node? = null

        // we pick a subtree that is less deep than the remaining depth (do..while loop)
        do newSubTree = other.nodes.random()
        while (newSubTree!!.depth > mixingPoint.depth)

        // we mix and deliver
        mixingPoint.replace(newSubTree)
        return Tree(chromosomeGenerator, fitnessFun, depth, chromosomeSample)
    }

    fun mutate() {
        /** Mutates the tree chromosome using a new sub-tree as a base. */
        val nodeToMutate: Node = nodes.random()
        val newSubTree: Node = Tree(
            chromosomeGenerator,
            fitnessFun,
            nextInt(nodeToMutate.depth)
            ).root

        // replace and update our serialization
        nodeToMutate.replace(newSubTree)
        nodes = root.serialize()
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