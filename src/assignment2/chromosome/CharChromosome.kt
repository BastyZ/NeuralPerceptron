package assignment2.chromosome

import assignment2.gene.CharGene
import kotlin.random.Random.Default.nextDouble

/**
 * Chromosome that represents a sequence of characters.
 *
 */
class CharChromosome : IChromosome<CharGene> {
    private val alphabet: String
    override val genes: Array<CharGene>
    override val target: Array<CharGene>

    /**
     * Create a new chromosome of the given `size`.
     *
     * @param size
     *    Number of genes in the chromosome.
     * @param aTarget
     *    **(Optional)** Target string of the chromosome.
     *    By default the chromosome has no target (target's an empty string).
     * @param alphabet
     *    **(Optional)** Set of valid characters that can take every gene.
     *    By default
     *    `0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ !"%$&/()=?`{[]}\+~*#';.:,-_<>|@^'`.
     */
    constructor(
        size: Int,
        aTarget: String = "",
        alphabet: String = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ !\"%\$&/()=?`{[]}\\+~*#';.:,-_<>|@^'"
    ) : this(size, aTarget, alphabet, null)

    /**
     * Secondary constructor.
     * Create a new chromosome from a given `genes` array.
     */
    private constructor(
        size: Int,
        aTarget: String,
        alphabet: String,
        genes: Array<CharGene>?
    ) {
        this.alphabet = alphabet
        this.genes = when (genes) {
            null -> Array(aTarget.length) { i -> CharGene(aTarget[i], alphabet) }
            else -> Array(size) { i -> genes[i] }
        }
        this.target = Array(aTarget.length) { i -> CharGene(aTarget[i], alphabet) }
    }

    override fun mutate(mutationRate: Double) {
        for (i in 0 until size)
            if (nextDouble() < mutationRate) genes[i] = CharGene(alphabet)
    }

    override fun replaceGeneAt(geneIndex: Int, value: Any) {
        genes[geneIndex].replaceWith(value as Char)
    }

    override fun copy(): CharChromosome {
        val genesCopy = Array(genes.size) { i -> genes[i].copy() }
        return CharChromosome(size, toString(target), alphabet, genesCopy)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is CharChromosome) return false
        if (other.size != this.size) return false
        return (0 until size).none { genes[it] != other.genes[it] }
    }

    /**
     * Computes a hash code value for the object.
     */
    override fun hashCode(): Int {
        var result = genes.contentHashCode()
        result = 31 * result + target.contentHashCode()
        return result
    }

    override fun toString(): String {
        return toString(genes)
    }

    /**
     * Returns the string representation of an array of genes.
     */
    private fun toString(genes: Array<CharGene>): String {
        val sb = StringBuilder()
        for (gene in genes) sb.append(gene.toString())
        return sb.toString()
    }
}