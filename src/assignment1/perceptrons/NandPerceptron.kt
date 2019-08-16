package assignment1.perceptrons

import assignment1.IPerceptron

class NandPerceptron(weights: Array<Double>, bias: Double):
    IPerceptron, AbstractPerceptron(weights, bias, 0.1) {

}