package assignment1

interface IActivationFun {

    /**
     * Applies a given function
     */
    fun apply(x: Double): Double

    /**
     * Applies the derivative of a given function
     */
    fun derivative(x: Double): Double

}