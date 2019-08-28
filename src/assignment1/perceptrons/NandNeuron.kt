package assignment1.perceptrons

import assignment1.INeuron

class NandNeuron(weights: Array<Double>, bias: Double):
    INeuron, AbstractPerceptron(weights, bias, 0.1) {

}