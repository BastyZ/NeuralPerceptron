package assignment3

import kotlin.math.max

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

class minFun(a: Node, b: Node): (Node, Node) -> Int {
    // Todo: terminar
    override fun invoke(a: Node, b: Node): Int = a.eval() + b.eval()
    override fun toString(): String = "+"
}

class multFun(a: Node, b: Node): (Node, Node) -> Int {
    // Todo: terminar
    override fun invoke(a: Node, b: Node): Int = a.eval() + b.eval()
    override fun toString(): String = "+"
}

class divFun(a: Node, b: Node): (Node, Node) -> Int {
    // Todo: terminar
    override fun invoke(a: Node, b: Node): Int = a.eval() + b.eval()
    override fun toString(): String = "+"
}
