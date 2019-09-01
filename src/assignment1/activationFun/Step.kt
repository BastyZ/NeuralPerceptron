package assignment1.activationFun

import assignment1.NeuralNetwork.IActivationFun

class Step: IActivationFun {
    override fun apply(x: Double): Double {
        return when {
            x > 0    -> 1.0
            else        -> 0.0
        }
    }

    override fun derivative(x: Double): Double {
        return 0.0
    }
}