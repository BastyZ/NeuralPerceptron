package assignment2.chromosome

import assignment2.gene.CharGene

/**
 * Factory for creating `CharChromosome`.
 *
 * @property  size
 *    Number of genes of the chromosome.
 * @property  aTarget
 *    Target of the chromosome.
 * @property  anAlphabet
 *    Chromosome's alphabet.
 */
class CharChromosomeFactory(
    private var size: Int,
    private var aTarget: String = "",
    private var anAlphabet: String = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ "
) : IChromosomeFactory<CharGene> {

    /** Builds a new `CharChromosome`. */
    override fun build() = CharChromosome(size, aTarget, anAlphabet)
}