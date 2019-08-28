package assignment1.activationFun

import assignment1.IActivationFun
import kotlin.math.exp

class Sigmoid: IActivationFun {
    override fun apply(x: Double): Double {
        return 1 / ( 1 + exp( -(x) ) )
    }

    override fun derivative(x: Double): Double {
        return this.apply(x) * (1 - this.apply(x))
    }
}