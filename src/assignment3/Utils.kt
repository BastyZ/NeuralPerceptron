package assignment3

import kotlin.reflect.jvm.reflect

// util fun from https://kotlinlang.org/docs/reference/reflection.html
// it is useful to access metadata of the function, like name, or it's parameters
fun funArgsCount(aFun: Function<Any>): Int {
    return aFun.reflect()!!.parameters.size
}
