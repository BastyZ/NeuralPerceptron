package assignment3.nodes

import assignment3.funArgsCount

open class Node(var function: ((Node, Node) -> Int)?, var depth: Int = 0) {
    var arguments: MutableList<Node> = mutableListOf<Node>()
    open var numArguments = when (function) {
        null -> 0
        else -> 2 // I'm lazy and do not want to do magic
    }

    open fun eval(): Int {
        check( arguments.size == numArguments ) {"the arguments passed doesn't match the expected arguments"}
        check(function != null) {"function is null"}
        return this.function!!(arguments.first(), arguments.last())
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
    }
}
