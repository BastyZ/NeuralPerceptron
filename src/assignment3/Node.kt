package assignment3

import kotlin.reflect.jvm.reflect
import assignment3.Node as Node

// util fun desde https://kotlinlang.org/docs/reference/reflection.html
// sirve para entregar info de la funcion, como el nombre, o sus parametros
fun funArgsCount(aFun: Function<Any>): Int {
    return aFun.reflect()!!.parameters.size
}

open class Node(var function: ((Node, Node) -> Int)?, var father: Node? = null) {
    // la clase recibe dos argumentos y retorna uno
    var arguments: MutableList<Node> = mutableListOf<Node>()
    open var numArguments = when (function) {
        null -> 0
        else -> funArgsCount(function!!)
    }

    open fun eval(): Int {
        check( arguments.size == numArguments ) {"La cantidad de argumentos no calza con la de argumentos"}
        check(function != null) {"la función es nula"}
        return this.function!!(arguments.first(), arguments.last())
    }

    fun serialize(): MutableList<Any> {
        val list: MutableList<Any> = mutableListOf(this)
        for (node in this.arguments) {
            list.add(node.serialize())
        }
        return list
    }

    fun copy(): Node {
        // TODO add deepcopy if necessary
        val aNode = Node(this.function, this.father)
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
    father: Node? = null,
    var left: Node,
    var right: Node
): Node(function = function, father = father) {
    override var numArguments = 2
    init {
        super.arguments.add(left)
        super.arguments.add(right)
    }

    override fun toString(): String {
        return function.toString()
    }
}

class TerminalNode(father: Node? = null, val value: Int)
    : Node(null, father) {
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

