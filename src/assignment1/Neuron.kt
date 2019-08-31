package assignment1

import assignment1.activationFun.Sigmoid
import kotlin.random.Random.Default.nextDouble

/**
 * Class representing a neuron, it has various constructors as syntactic sugar
 *  Neuron(n: Int) creates a neuron with n random weights between [0,1) using Sigmoid's activation fun
 *  Neuron(n: Int, fun: IActivationFun) does the above with custom activation fun
 *  with the primary one you set manually all the parameters of the Neuron
 *
 *  @author Bastián Inostroza
 */
class Neuron(
    private var weights: MutableList<Double>,
    private val activationFun: IActivationFun,
    private var bias: Double = 1.0,
    private val learningRate: Double = 0.01
    ): INeuron {

    // basic constructor that receives the number of weights and the activation function
    constructor(nOfWeights: Int,
                function: IActivationFun
                ): this(
                        MutableList(nOfWeights) { nextDouble()},
                        function
                        )

    // fast constructor that only receives the number of weights
    constructor(nOfWeights: Int): this(nOfWeights, Sigmoid())

    // Delta for error back-propagation
    private var delta: Double = 0.0

    override fun feed(inputs: List<Double>): Double {
        assert(weights.size == inputs.size) {
            "Weights and Inputs lengths does not match"
        }
        var acc = 0.0
        for (i in inputs.indices) {
            acc += weights[i] * inputs[i]
        }

        return activationFun.apply(acc + bias)
    }

    /**
     * When back-propagating errors, sets the new delta
     * according to the error and the actual output of this neuron
      */
    internal fun adjustDeltaWith(error: Double, output: Double) {
        delta = error * activationFun.derivative(output)
    }

    /**
     * Gives the error associated to a specific weight, used by the NeuronLayer
     */
    internal fun errorForWeight(weightIndex: Int) : Double {
        return weights[weightIndex] * delta
    }

    override fun train(inputs: List<Double>) {
        inputs.withIndex().forEach { (i, anInput) ->
            weights[i] += anInput * delta * learningRate
        }
        bias += learningRate * delta
    }
}