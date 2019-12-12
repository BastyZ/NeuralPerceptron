package assignment3.tree

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
