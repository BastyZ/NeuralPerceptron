package assignment3

import assignment3.nodes.Node
import kotlin.math.max
import kotlin.math.min

/**
 *  Using the function-type interface as shown here:
 *    https://github.com/JetBrains/kotlin/blob/master/spec-docs/function-types.md
 *
 *  And overriding 'toString()' to make it look pretty after
 *
 *  PS: I did'nt want and try to do it for N nodes, sounds too messy for the function types
 *
 *  PS2: Believe me, this is the most __beautiful__ way to do this
 */

class addFun: (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int {
        return a.eval() + b.eval()
    }
    override fun toString(): String = "+"
}

class subFun: (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int {
        return a.eval() - b.eval()
    }
    override fun toString(): String = "-"
}

class maxFun: (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int {
        return max(a.eval(), b.eval())}
    override fun toString(): String = "max"
}

class minFun: (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int {
        return min(a.eval(), b.eval())
    }
    override fun toString(): String = "min"
}

class multFun: (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int {
        return a.eval() * b.eval()
    }
    override fun toString(): String = "*"
}

class divFun: (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int {
        return when (b.eval()) {
            // we handle the division by zero throwing the appropriate exception
            0 -> throw ArithmeticException("/ by zero")
            else -> a.eval() / b.eval()
        }
    }
    override fun toString(): String = "/"
}
