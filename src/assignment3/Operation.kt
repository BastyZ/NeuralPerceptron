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
    override fun toString(): String = "${this.a} + ${this.b}"
}

class subFun(val a: Node, val b: Node): (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int = a.eval() - b.eval()
    override fun toString(): String = "${this.a} - ${this.b}"
}

class maxFun(val a: Node, val b: Node): (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int = max(a.eval(), b.eval())
    override fun toString(): String = "max(${this.a}, ${this.b})"
}

class minFun(val a: Node, val b: Node): (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int = min(a.eval(), b.eval())
    override fun toString(): String = "min(${this.a}, ${this.b})"
}

class multFun(val a: Node, val b: Node): (Node, Node) -> Int {
    override fun invoke(a: Node, b: Node): Int = a.eval() * b.eval()
    override fun toString(): String = "${this.a} * ${this.b}"
}

class divFun(val a: Node, val b: Node): (Node, Node) -> Int {
    // Todo: hacer caso division cero
    override fun invoke(a: Node, b: Node): Int = a.eval() / b.eval()
    override fun toString(): String = "${this.a} / ${this.b}"
}
