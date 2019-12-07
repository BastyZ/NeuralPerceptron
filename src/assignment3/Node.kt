package assignment3

import kotlin.reflect.jvm.reflect

// util fun desde https://kotlinlang.org/docs/reference/reflection.html
// sirve para entregar info de la funcion, como el nombre, o sus parametros
fun funArgsCount(aFun: Function<Any>): Int {
    return aFun.reflect()!!.parameters.size
}

data class Node(var function: (Node, Node) -> Unit, var father: Node? = null) {
    // la clase recibe dos argumentos y retorna uno
    private var arguments: MutableList<Node> = mutableListOf<Node>()
    private var numArguments = funArgsCount(function)

    fun eval() {
        check( arguments.size == numArguments ) {"La cantidad de argumentos no calza con la de argumentos"}
        return this.function(arguments.first(), arguments.last())
    }

    fun serialize(): MutableList<Any> {
        var list: MutableList<Any> = mutableListOf(this)
        for (node in this.arguments) {
            list.add(node.serialize())
        }
        return list
    }

    fun copy(): Node{
        // TODO add deepcopy
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
