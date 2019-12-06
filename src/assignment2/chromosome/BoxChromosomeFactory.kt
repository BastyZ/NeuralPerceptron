package assignment2.chromosome

import assignment2.Box
import assignment2.gene.BoxGene

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
class BoxChromosomeFactory(
    private var size: Int,
    private var aTarget: Array<BoxGene> = Array<BoxGene>(1) { BoxGene(Box.ZERO, Box.values() ) },
    private var anAlphabet: Array<Box> = Box.values()
) : IChromosomeFactory<BoxGene> {

    /** Builds a new `BoxChromosome`. */
    override fun build() = BoxChromosome(size, aTarget, anAlphabet)
}
