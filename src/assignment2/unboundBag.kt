package assignment2

import assignment2.chromosome.BoxChromosomeFactory
import assignment2.gene.BoxGene

fun main() {
    /**
     * Ejecuta el ejercicio de encontrar una palabra o frase
     */
    val phrase: Array<Box> = arrayOf(Box.FIVE, Box.FIVE, Box.FIVE, Box.THREE, Box.THREE, Box.THREE)

    val factory = BeingFactory(
        factories = *arrayOf(
            BoxChromosomeFactory(
                phrase.size,
                Array<BoxGene>(phrase.size) { BoxGene(phrase[it], Box.values()) }
            )
        ),
        mutationRate = .2,
        fitnessFunction = ::boxFitness,
        filterFunction = ::boxFilter
    )

    val population = Population(10000, factory)
    var fittest = population.getFittest()

    var bestFit = doubleArrayOf(0.0)
    var generation = 0
    // TODO edit
    while (generation != 10000) {
        population.evolve()
        bestFit = population.getFittest().fitness
        println(" :: Gen: $generation | Fit ${bestFit[0]} | Best Guess is ${population.getFittest().toString()}")
        generation++
    }

    println("The best guess is: ${population.getFittest().toString()} in generation $generation")
}

fun boxFitness(being: Being): DoubleArray {

}

fun boxFilter(being: Being) {

}
