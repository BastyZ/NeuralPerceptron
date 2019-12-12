package assignment3.nodes


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
