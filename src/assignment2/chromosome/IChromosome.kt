package assignment2.chromosome

import assignment2.gene.IGene

/**
 * A chromosome consists of an array of genes.
 *
 * @author  Bastián Inostroza
 * @see IGene
 */
interface IChromosome<Gene : IGene<*>> {

    /** Genes that make up the chromosome. */
    val genes: Array<Gene>

    /** Number of genes in the chromosome. */
    val size: Int
        get() = genes.size

    /** Creates a copy of this chromosome. */
    fun copy(): IChromosome<Gene>

    /**
     * Mutates a chromosome according to it's mutation rate.
     *
     * @param mutationRate  Probability with which a gene will mutate.
     */
    fun mutate(mutationRate: Double)

    val target: Array<Gene>

    fun replaceGeneAt(geneIndex: Int, value: Any)

}
