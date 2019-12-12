package assignment3.tree

import assignment3.funArgsCount
import assignment3.tree.Node as Node

open class Node(var function: ((Node, Node) -> Int)?) {
    var arguments: MutableList<Node> = mutableListOf<Node>()
    open var numArguments = when (function) {
        null -> 0
        else -> funArgsCount(function!!)
    }

    open fun eval(): Int {
        check( arguments.size == numArguments ) {"the arguments passed doesn't match the expected arguments"}
        check(function != null) {"function is null"}
        return this.function!!(arguments.first(), arguments.last())
    }

    fun serialize(): MutableList<Any> {
        val list: MutableList<Any> = mutableListOf(this)
        for (node in this.arguments) {
            list.addAll(node.serialize())
        }
        return list
    }

    fun copy(): Node {
        val aNode = Node(this.function)
        aNode.arguments = this.arguments
        return aNode
    }

    fun replace(otherNode: Node) {
        this.function = otherNode.function
        this.arguments = otherNode.arguments
        this.numArguments = otherNode.numArguments
    }
}
