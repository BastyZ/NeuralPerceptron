package assignment3.nodes

import kotlin.test.assertNotNull


open class Node(var function: ((Node, Node) -> Int)?, var depth: Int = 0) {
    var arguments: MutableList<Node> = mutableListOf<Node>()
    open var value: Int = 0
    open var numArguments = when (function) {
        null -> 0
        else -> 2 // I'm lazy and do not want to do magic
    }

    open fun eval(): Int {
        check( arguments.size == numArguments ) {"the arguments passed doesn't match the expected arguments"}
        when { // This is an special case, given that we cannot convert from TerminalNode to Node
            function == null && depth == 0 -> return value
        }
        value = this.function!!(arguments.first(), arguments.last())
        return value
    }

    fun serialize(): MutableList<Node> {
        val list: MutableList<Node> = mutableListOf(this)
        for (node in this.arguments) {
            list.addAll(node.serialize())
        }
        return list
    }

    open fun copy(): Node {
        val aNode = Node(this.function, depth)
        // we made copies of all children
        aNode.arguments = mutableListOf<Node>()
        this.arguments.forEach { aNode.arguments.add(it.copy()) }

        return aNode
    }

    fun replace(otherNode: Node) {
        this.function = otherNode.function
        this.arguments = otherNode.arguments
        this.numArguments = otherNode.numArguments
        this.depth = otherNode.depth
        this.value = otherNode.value
    }
}
