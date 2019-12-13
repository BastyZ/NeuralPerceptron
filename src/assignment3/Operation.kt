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
    lateinit var a: Node
    lateinit var b: Node
    override fun invoke(a: Node, b: Node): Int {
        this.a = a
        this.b = b
        return a.eval() + b.eval()
    }
    override fun toString(): String = "${this.a.eval().toString()} + ${this.b.eval().toString()}"
}

class subFun: (Node, Node) -> Int {
    lateinit var a: Node
    lateinit var b: Node
    override fun invoke(a: Node, b: Node): Int {
        this.a = a
        this.b = b
        return a.eval() - b.eval()
    }
    override fun toString(): String = "${this.a.eval().toString()} - ${this.b.eval().toString()}"
}

class maxFun: (Node, Node) -> Int {
    lateinit var a: Node
    lateinit var b: Node
    override fun invoke(a: Node, b: Node): Int {
        this.a = a
        this.b = b
        return max(a.eval(), b.eval())}
    override fun toString(): String = "max(${this.a.eval().toString()}, ${this.b.eval().toString()})"
}

class minFun: (Node, Node) -> Int {
    lateinit var a: Node
    lateinit var b: Node
    override fun invoke(a: Node, b: Node): Int {
        this.a = a
        this.b = b
        return min(a.eval(), b.eval())
    }
    override fun toString(): String = "min(${this.a.eval().toString()}, ${this.b.eval().toString()})"
}

class multFun: (Node, Node) -> Int {
    lateinit var a: Node
    lateinit var b: Node
    override fun invoke(a: Node, b: Node): Int {
        this.a = a
        this.b = b
        return a.eval() * b.eval()
    }
    override fun toString(): String = "${this.a.eval().toString()} * ${this.b.eval().toString()}"
}

class divFun: (Node, Node) -> Int {
    lateinit var a: Node
    lateinit var b: Node
    override fun invoke(a: Node, b: Node): Int {
        this.a = a
        this.b = b
        return when (b.eval()) {
            // we handle the division by zero throwing the appropriate exception
            0 -> throw ArithmeticException("/ by zero")
            else -> a.eval() / b.eval()
        }
    }
    override fun toString(): String = "${this.a.eval().toString()} / ${this.b.eval().toString()}"
}
