package assignment2

import assignment2.chromosome.BoxChromosomeFactory
import assignment2.gene.BoxGene
import kotlin.math.abs

fun main() {
    /**
     * Ejecuta el ejercicio de encontrar una palabra o frase
     */
    val phrase: Array<Box> = arrayOf(Box.FIVE, Box.FIVE, Box.FIVE, Box.THREE, Box.THREE, Box.THREE)

    val factory = BeingFactory(
        factories = *arrayOf(
            BoxChromosomeFactory(
                phrase.size,
                Array<BoxGene>(phrase.size) { BoxGene(Box.values()) }
            )
        ),
        mutationRate = .2,
        fitnessFunction = ::boxFitness,
        filterFunction = ::boxFilter
    )

    val population = Population(6, factory)

    var bestFit = doubleArrayOf(0.0)
    var generation = 0

    while ( abs(bestFit[0]) < 36.0) {
        population.evolve()
        bestFit = population.getFittest().fitness
        println(" :: Gen: $generation | Fit ${bestFit[0]} | Best Guess is ${population.getFittest().toString()}")
        generation++
    }

    val winner = population.getFittest().genotype[0].genes
    var weight = 0
    var value = 0
    for (boxGene in winner) {
        val box = boxGene.dna as Box
        weight += box.weight
        value += box.value
    }
    println("The best guess is: ${population.getFittest().toString()} in generation $generation \n" +
            "  with weight $weight and value $value")
}

fun boxFitness(being: Being): DoubleArray {
    val genotype = being.genotype[0]
    var weight = 0
    var value = 0
    for (boxGene in genotype.genes) {
        val box = boxGene.dna as Box
        weight += box.weight
        value += box.value
    }

    return when {
        weight > 15 -> doubleArrayOf(0.0)
        else        -> doubleArrayOf(value.toDouble())
    }
}

fun boxFilter(being: Being) {

}
