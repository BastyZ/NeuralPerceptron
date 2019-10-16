package assignment2

import assignment2.chromosome.CharChromosomeFactory

fun main() {
    /**
     * Ejecuta el ejercicio de encontrar una palabra o frase
     */
    val phrase: String = "Hello World! Welcome to Kotlin!"

    val factory = BeingFactory(
        factories = *arrayOf(CharChromosomeFactory(phrase.length, phrase)),
        mutationRate = .4,
        fitnessFunction = ::fitness,
        filterFunction = ::filter
    )

    val population = Population(1000, factory)
    var fittest = population.getFittest()

    var stability = 0
    while (stability <= 100) {
        population.evolve()
        println(" :: Best Guess is ${population.getFittest().toString()}")
        when (fittest) {
            population.getFittest() -> stability++
            else -> {
                stability = 0
                fittest = population.getFittest()
            }
        }
    }

    println("The best guess is: $fittest \n with fitness ${fittest.fitness[0]}")
}

fun fitness(being: Being): DoubleArray {
    var guessQuality = 0.0
    val chromosome = being.genotype[0]
    for ( gene in chromosome.genes.withIndex()) {
        when (gene.value) {
            chromosome.target[gene.index] -> guessQuality++
        }
    }
    return doubleArrayOf(guessQuality)
}

fun filter(being: Being) {

}
