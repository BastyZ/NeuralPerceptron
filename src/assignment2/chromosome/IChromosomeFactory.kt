package assignment2.chromosome

import assignment2.gene.IGene


/**
 * Factory for creating chromosomes.

 */
interface IChromosomeFactory<G : IGene<*>> {

    /** Builds a new chromosome. */
    fun build(): IChromosome<G>
}