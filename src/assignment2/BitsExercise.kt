package assignment2

import assignment2.chromosome.CharChromosomeFactory

fun main() {
    /**
     * Ejecuta el ejercicio de encontrar una palabra o frase
     */
    val phrase: String = "10101010101010110110111010101010101010101010101010101010101011"
    val alphabet: String = "01"

    val factory = BeingFactory(
        factories = *arrayOf(CharChromosomeFactory(
            phrase.length,
            phrase,
            alphabet
            )),
        mutationRate = .2,
        fitnessFunction = ::bitsFitness,
        filterFunction = ::bitsFilter
    )

    val population = Population(10000, factory)
    var fittest = population.getFittest()

    var bestFit = doubleArrayOf(0.0)
    var generation = 0
    while (bestFit[0] < phrase.length) {
        population.evolve()
        bestFit = population.getFittest().fitness
        println(" :: Gen: $generation | Fit ${bestFit[0]} | Best Guess is ${population.getFittest().toString()}")
        generation++
    }

    println("The best guess is: ${population.getFittest().toString()} in generation $generation")
}

fun bitsFitness(being: Being): DoubleArray {
    var guessQuality = 0.0
    val chromosome = being.genotype[0]
    for ( gene in chromosome.genes.withIndex()) {
        if (gene.value == chromosome.target[gene.index]) guessQuality++
    }
    return doubleArrayOf(guessQuality)
}

fun bitsFilter(being: Being) {

}
