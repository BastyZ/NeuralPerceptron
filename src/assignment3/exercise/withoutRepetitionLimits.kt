package assignment3.exercise

import LinePlot
import assignment3.*
import assignment3.nodes.Node
import assignment3.tree.Ast
import kotlin.math.abs

fun withoutRepetitionLimits() {
    // this section we'll be in spanish
    println("Encontrar número sin límite de repetición")
    // parametros del enunciado
    val population = 200
    val depth = 15
    val mutationRate = 0.3
    val functions: MutableList<(Node, Node) -> Int> = mutableListOf(
        addFun(),
        subFun(),
        multFun(),
        maxFun()
    )
    val terminals: MutableList<Int> = mutableListOf(25, 7, 8, 100, 4, 2)
    val generator = Ast(functions, terminals, terminalNodeProbability = 0.3)
    // funcion de fitness
    fun noRepetitionFitness(a: Node) = 65346 - abs(65346 - a.eval())

    // ahora si creamos el bosque
    val forest =
        Forest(population, generator, ::noRepetitionFitness, depth, mutationRate)

    // meta de las corridas
    var gen = 0
    val fitnessList: MutableList<Double> = mutableListOf()

    // do while .... feeling old ?
    do {
        fitnessList.add(forest.fittestFitness().toDouble())
        print("\r generacion ${gen++} distancia: ${ abs(65346- forest.fittestFitness())}")
        forest.evolve()
    } while (fitnessList.last().toInt() != 65346)
    println("\n Encontrado número en la generación $gen")

    // gráfico
    val plot = LinePlot("Sin limite de repeticiones", "Generaciones", "Fitness", "fitness")
    plot.yData = fitnessList.toDoubleArray()
    plot.drawOneLine()
}