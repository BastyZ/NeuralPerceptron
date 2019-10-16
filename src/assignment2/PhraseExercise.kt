package assignment2

import assignment2.chromosome.CharChromosomeFactory

fun main() {
    /**
     * Ejecuta el ejercicio de encontrar una palabra o frase
     */
    val phrase: String = "Welcome to Kotlin!"

    val factory = BeingFactory(
        factories = *arrayOf(CharChromosomeFactory(
            phrase.length,
            phrase
            )),
        mutationRate = .2,
        fitnessFunction = ::fitness,
        filterFunction = ::filter
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

    println("The best guess is: ${population.getFittest().toString()} \n with fitness ${bestFit[0]}")
}

fun fitness(being: Being): DoubleArray {
    var guessQuality = 0.0
    val chromosome = being.genotype[0]
    for ( gene in chromosome.genes.withIndex()) {
        if (gene.value == chromosome.target[gene.index]) guessQuality++
    }
    return doubleArrayOf(guessQuality)
}

fun filter(being: Being) {

}
