package assignment3

import assignment3.nodes.Node


/** Default/Example values for you */
const val examplePopulation = 20
val allFunctions: MutableList<(Node, Node) -> Int> = mutableListOf(
    addFun(),
    subFun(),
    maxFun(),
    minFun(),
    multFun(),
    divFun() // div by zero not handled
)
val terminalExample = mutableListOf<Int>(1, 2, 3, 4) // all the numbers you could want

fun main() {
    // You can run any exercise that I made here, or you can try and run a test
    // You can use ctrl + B to jump to the exercise function

    // I do not know how to work when the best fitness is zero, so I'll work around that
    ::withoutRepetitionLimits.invoke()
}

