package assignment1.activationFun

import assignment1.IActivationFun
import kotlin.math.exp
import kotlin.math.pow

class Tanh: IActivationFun {
    override fun apply(x: Double): Double {
        return ( exp(x) - exp(-x) ).div( exp(x) + exp(-x) )
    }

    override fun derivative(x: Double): Double {
        return 1 - this.apply(x).pow(2)
    }
}