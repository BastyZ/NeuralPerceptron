package assignment3

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

class addFun(val a: Node, val b: Node): (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int = a.eval() + b.eval()
    override fun toString(): String = "${this.a.eval().toString()} + ${this.a.eval().toString()}"
}

class subFun(val a: Node, val b: Node): (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int = a.eval() - b.eval()
    override fun toString(): String = "${this.a.eval().toString()} - ${this.a.eval().toString()}"
}

class maxFun(val a: Node, val b: Node): (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int = max(a.eval(), b.eval())
    override fun toString(): String = "max(${this.a.eval().toString()}, ${this.a.eval().toString()})"
}

class minFun(val a: Node, val b: Node): (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int = min(a.eval(), b.eval())
    override fun toString(): String = "min(${this.a.eval().toString()}, ${this.a.eval().toString()})"
}

class multFun(val a: Node, val b: Node): (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int = a.eval() * b.eval()
    override fun toString(): String = "${this.a.eval().toString()} * ${this.a.eval().toString()}"
}

class divFun(val a: Node, val b: Node): (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int {
        return when (b.eval()) {
            // we handle the division by zero throwing the appropriate exception
            0 -> throw ArithmeticException("/ by zero")
            else -> a.eval() / b.eval()
        }
    }
    override fun toString(): String = "${this.a.eval().toString()} / ${this.a.eval().toString()}"
}
