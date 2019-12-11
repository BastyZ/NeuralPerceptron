package assignment3

import kotlin.random.Random.Default.nextDouble

class Ast(
    internal val allowedFunctions: MutableList<(Node, Node) -> Int>,
    internal val allowedTerminalValues: MutableList<Int>,
    internal val terminalNodeProbability: Double = 0.3
) {
    fun invoke(maxDepth: Int): Node {
        // aux fun
         fun createRecTree(depth:Int): Node {
            when {
                depth > 0 -> {
                    // we choose a random function
                    val nodeFun = allowedFunctions.random()
                    // we create it's children randomly
                    val children: List<Node> = List(2) {
                        // there is a possibility of don't keep creating sub-trees
                        // for the entire eternity
                        when (nextDouble() < terminalNodeProbability) {
                            true -> createRecTree(0)
                            false -> createRecTree(depth - 1)
                        }
                    }

                    // we do not have (I think) unpacking here, be we know they're only two
                    return BinaryNode(nodeFun, children.first(), children.last())
                }
                else -> return TerminalNode(value = allowedTerminalValues.random())
            }
        }

        return createRecTree(maxDepth)
    }
}