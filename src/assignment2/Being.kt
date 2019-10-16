package assignment2

import assignment2.chromosome.IChromosome

/**
 * Being of a population, it is made of a set of chromosomes.
 *
 * @constructor
 *    Creates an being from a set of particular chromosomes.
 */
class Being(
    vararg chromosomes: IChromosome<*>, // Chromosomes that make the genotype of the beings.
    private val mutationRate: Double, // Mutation rate of the genes of the beings.
    private val fitnessFunction: ((Being) -> DoubleArray)? = null, // Custom fitness function.
    filterFunction: ((Being) -> Unit)? = null
) : Comparable<Being> {

    var fitness: DoubleArray = doubleArrayOf(0.0)

    internal val size: Int = chromosomes.size

    var genotype = Array(chromosomes.size) { i -> chromosomes[i] }
        private set

    private val filter: (Being) -> Unit

    init {
        filter = filterFunction ?: { being: Being -> defaultFilter(being) }
        filter(this)
        updateFitness()
    }

    /**
     * Default filter of the individual. Does nothing.
     */
    @Suppress("UNUSED_PARAMETER")
    private fun defaultFilter(being: Being) {
        return
    }


    /** Generates an offspring from 2 parents. */
    internal fun crossover(other: Being, mixingPoint: Int): Being {
        assert(size == other.size) {"Size of the beings must match, cannot crossover." }
        val offspringGenotype = Array(size) {
                i -> crossover(genotype[i], other.genotype[i], mixingPoint)
        }
        return Being(
            *offspringGenotype,
            mutationRate = mutationRate,
            fitnessFunction = fitnessFunction,
            filterFunction = filter
        )
    }

    /** does a crossover of two parents. */
    private fun crossover(
        aChromosome: IChromosome<*>,
        anotherChromosome: IChromosome<*>,
        mixingPoint: Int
    ): IChromosome<*> {
        val offspring = aChromosome.copy()

        (0 until offspring.size)
            .filter { it > mixingPoint }
            .forEach { anotherChromosome.genes[it].copyTo(offspring.genes[it]) }
        return offspring
    }

    /**
     * Mutates the being according to it's mutation rate.
     */
    internal fun mutate() {
        for (chromosome in genotype) chromosome.mutate(mutationRate)
        filter(this)
        updateFitness()
    }

    fun replaceGeneAt(chromosomeIndex: Int, geneIndex: Int, with: Any) {
        genotype[chromosomeIndex].replaceGeneAt(geneIndex, with)
        updateFitness()
    }

    /**
     * Changes the fitness of the being according to the fitness function.
     */
    private fun updateFitness() {
        fitness = fitnessFunction?.invoke(this) ?: defaultFitness(this)
    }

    /**
     * Calculates the fitness of an being.
     */
    private fun defaultFitness(being: Being): DoubleArray {
        val fit = being.genotype
            .map { chromosome ->
                (chromosome.target.indices)
                    .count { chromosome.genes[it] == chromosome.target[it] }
            }
            .map { it.toDouble() }
        return fit.toDoubleArray()
    }

    override fun compareTo(other: Being): Int {
        for (i in fitness.indices) {
            return if (fitness[i] == other.fitness[i])
                continue
            else if (fitness[i] > other.fitness[i])
                1
            else
                -1
        }
        return 0
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0 until size) {
            sb.append(genotype[i].toString())
            if (i < size - 1) sb.append(", ")
        }
        return sb.toString()
    }

    /**
     * Returns true if this individual is equal to another.
     */
    override fun equals(other: Any?): Boolean {
        if (other !is Being) return false
        if (other.size != this.size) return false
        return (0 until size).none { genotype[it] != other.genotype[it] }
    }

    /**
     * Returns the hash code of this object.
     */
    override fun hashCode(): Int {
        var result = fitness.contentHashCode()
        result = 31 * result + size
        result = 31 * result + genotype.contentHashCode()
        return result
    }
}