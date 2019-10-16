package assignment2

import assignment2.chromosome.IChromosomeFactory

class BeingFactory (
    private vararg val factories: IChromosomeFactory<*>,
    private val mutationRate: Double,
    private val fitnessFunction: ((Being) -> DoubleArray)? = null,
    private val filterFunction: ((Being) -> Unit)? = null
) {

    /** Builds an individual. */
    fun build(): Being {
        val genotype = Array(factories.size) { i -> factories[i].build() }
        return Being(
            *genotype,
            mutationRate = mutationRate,
            fitnessFunction = fitnessFunction,
            filterFunction = filterFunction
        )
    }
}