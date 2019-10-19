package assignment2.chromosome

import assignment2.Box
import assignment2.gene.BoxGene
import kotlin.random.Random.Default.nextDouble

/**
 * Chromosome that represents a sequence of characters.
 */
class BoxChromosome : IChromosome<BoxGene> {
    private val alphabet: Array<Box>
    override val genes: Array<BoxGene>
    override val target: Array<BoxGene>

    /**
     * Create a new chromosome of the given `size`.
     *
     * @param size
     *    Number of genes in the chromosome.
     * @param aTarget
     *    Target string of the chromosome.
     *    By default the chromosome has no target (target's an empty string).
     * @param alphabet
     *    Set of valid boxes that can take every gene.
     */
    constructor(
        size: Int,
        aTarget: Array<BoxGene>,
        alphabet: Array<Box> = Box.values()
    ) : this(size, aTarget, alphabet, null)

    /**
     * Secondary constructor.
     * Create a new chromosome from a given `genes` array.
     */
    private constructor(
        size: Int,
        aTarget: Array<BoxGene>,
        alphabet: Array<Box> = Box.values(),
        genes: Array<BoxGene>?
    ) {
        this.alphabet = alphabet
        this.genes = when (genes) {
            null -> Array(alphabet.size) { BoxGene(alphabet) }
            else -> Array(size) { i -> genes[i] }
        }
        this.target = aTarget
    }

    override fun mutate(mutationRate: Double) {
        for (i in 0 until size)
            if (nextDouble() < mutationRate) genes[i] = BoxGene(alphabet)
    }

    override fun replaceGeneAt(geneIndex: Int, value: Any) {
        genes[geneIndex].replaceWith(value as Box)
    }

    override fun copy(): BoxChromosome {
        val genesCopy: Array<BoxGene> = Array(genes.size) { i -> genes[i].copy() }
        return BoxChromosome(size, target, alphabet, genesCopy)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is BoxChromosome) return false
        if (other.size != this.size) return false
        return (0 until size).none { genes[it] != other.genes[it] }
    }

    /**
     * Computes a hash code value for the object.
     */
    override fun hashCode(): Int {
        var result = genes.contentHashCode()
        result = 31 * result + target.hashCode()
        return result
    }

    override fun toString(): String {
        return this.toString(genes)
    }

    /**
     * Returns the string representation of an array of genes.
     */
    private fun toString(genes: Array<BoxGene>): String {
        val builtString = StringBuilder()
        for (gene in genes) builtString.append(gene.toString())
        return builtString.toString()
    }
}