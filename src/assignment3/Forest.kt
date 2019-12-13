package assignment3

import assignment3.nodes.Node
import assignment3.tree.Ast
import assignment3.tree.Tree
import kotlin.random.Random.Default.nextInt

class Forest(
    val size: Int,
    chromosomeGenerator: Ast,
    fitnessFun: (Node) -> Int,
    depth: Int = 8,
    val mutationRate: Double = 0.1
    ) {

    private var trees: MutableList<Tree>

    init {
        // We create a list of trees with them initialized
        trees = MutableList(size) { Tree(chromosomeGenerator, fitnessFun, depth) }
        trees.sort() // they get sorted by fitness (see CompareTo on Tree.kt)
    }

    fun evolve() {
        /** Evolves the forest to a next generation. */
        val children: MutableList<Tree> = mutableListOf() // empty list
        val survivors: Int = trees.size / 3
        var index = 0

        while (children.size < trees.size){
            when {
                index < trees.size - survivors - 1 -> {
                    val parentOne: Tree = tournamentSelection()
                    val parentTwo: Tree = tournamentSelection()

                    children.addAll(doubleCrossover(parentOne, parentTwo))
                }
                else -> {
                    // they can mutate too
                    trees[index].mutate(mutationRate)
                    children.add(trees[index++])
                }
            }
        }
        // we establish the new generation of trees
        trees = children
        trees.sort()
    }

    /** Returns the fittest individual. */
    fun getFittest() = trees.last() // the get sorted from minor to major on fitness

    /** Returns the best fitness from all trees */
    fun fittestFitness() = getFittest().fitness

    private fun doubleCrossover(aTree: Tree, otherTree: Tree): List<Tree> {
        /** Performs two crossovers between two trees */
        val one = aTree.crossover(otherTree)
        val other = otherTree.crossover(aTree)
        one.mutate(mutationRate)
        other.mutate(mutationRate)
        return listOf(one, other)
    }

    private fun tournamentSelection(): Tree {
        /** Selects a random individual prioritizing the ones with greater fitness. */
        // maxOf returns the tree with more fitness (the same as Population.kt but shorter)
        return trees[ maxOf(nextInt(trees.size), nextInt(trees.size)) ]
    }

}