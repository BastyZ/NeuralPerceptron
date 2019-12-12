package assignment3

import kotlin.reflect.jvm.reflect
import assignment3.Node as Node

// util fun from https://kotlinlang.org/docs/reference/reflection.html
// it is useful to access metadata of the function, like name, or it's parameters
fun funArgsCount(aFun: Function<Any>): Int {
    return aFun.reflect()!!.parameters.size
}

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

class BinaryNode(
    function: (Node, Node) -> Int,
    var left: Node,
    var right: Node
): Node(function = function) {
    override var numArguments = 2
    init {
        super.arguments.add(left)
        super.arguments.add(right)
    }

    override fun toString(): String {
        return function.toString()
    }
}

class TerminalNode(val value: Int)
    : Node(null) {
    /**
     *  Represents the leaf of a tree
     */
    override fun eval(): Int {
        return value
    }

    override fun toString(): String {
        return this.value.toString()
    }
}

