package assignment3

data class Node(var function: (Node, Node) -> Unit, var father: Node? = null) {
    // la clase recibe dos argumentos y retorna uno
    private var arguments: MutableList<Node> = mutableListOf<Node>()
    private var numArguments = 2

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
