package assignment3.nodes


class BinaryNode(
    function: (Node, Node) -> Int,
    depth: Int,
    var left: Node,
    var right: Node
): Node(function = function, depth = depth) {
    override var numArguments = 2
    init {
        super.arguments.add(left)
        super.arguments.add(right)
    }

    override fun toString(): String {
        return "(${function.toString()} $left $right)"
    }
}
