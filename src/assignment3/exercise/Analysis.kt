package assignment3.exercise

import assignment3.*
import assignment3.nodes.Node
import assignment3.tree.Ast
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun analysis() {
    // this section we'll be in spanish
    println("Análisis de mejor combinacion Población y mutación")
    // parametros del enunciado
    val population: List<Int> = List(20) {index: Int -> 50 * (index + 1) }
    val depth = 15
    val mutationRate: List<Double> = List(10) {index: Int -> 0.1 * index }

    // esto es lo mismo que el analisis sin repeticion pero con mas casos
    val terminals: MutableList<Int> = mutableListOf(25, 7, 8, 100, 4, 2)
    // el generador y funciones se mantienen
    val functions: MutableList<(Node, Node) -> Int> = mutableListOf(
        addFun(),
        subFun(),
        multFun(),
        maxFun()
    )
    val generator = Ast(functions, terminals, terminalNodeProbability = 0.4)
    // funcion de fitness
    fun noRepetitionFitness(a: Node) = 65346 - abs(65346 - a.eval())

    println("generating matrix")
    // Creamos una matriz y corremos reachFitness con cada configuración correspondiente
    val matrix: Array2D<Long> = Array2D(population.size, mutationRate.size) {
            x: Int, y: Int -> reachFitness(
        Forest(
            population[x],
            generator,
            ::noRepetitionFitness,
            depth,
            mutationRate = mutationRate[y]
        )
    ) }

    val (x, y, minimum) = minOf(matrix)
    println("La mejor combinación es (con profundidad $depth): \n" +
            "\t población: ${population[x]} \n" +
            "\t ratio de mutacion: ${mutationRate[y]} \n" +
            "\t con un tiempo de $minimum")
}

private fun minOf(an: Array2D<Long>): Triple<Int, Int, Long> {
    var minimum:Long = an.array[1][1]
    var x: Int = 0
    var y: Int = 0
    for (i in an.array.indices) {
        for (j in an.array[i].indices) {
            if (minimum > an.array[i][j]) {
                minimum = an.array[i][j]
                x = i
                y = j
            }
        }
    }
    return Triple(x, y, minimum)
}

private fun reachFitness(forest: Forest): Long {
    println("Comenzando para size:${forest.size} y rate: ${forest.mutationRate}")
    val time = measureTimeMillis {
        repeat(20) {
            // we are doing the minimum of 10 times for each config
            var gen = 0
            // do while .... feeling old ?
            do {
                forest.evolve()
                // this makes it interactive
                print("\rrun ->$it generaciones: $gen")
                gen++
            } while (forest.fittestFitness() != 65346 && gen < 500)
            print("\n")
            // before starting over we make another forest
            forest.reset()
        }
    }
    println("Tiempo para size:${forest.size} y rate: ${forest.mutationRate} es $time \n")
    return time
}