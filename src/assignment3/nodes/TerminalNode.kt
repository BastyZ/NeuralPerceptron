package assignment3.nodes

class TerminalNode(val value: Int)
    : Node(null, 0) {
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
