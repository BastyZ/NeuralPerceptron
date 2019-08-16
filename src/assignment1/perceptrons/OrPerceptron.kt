package assignment1.perceptrons

import assignment1.IPerceptron

class OrPerceptron(weights: Array<Double>, bias: Double):
    IPerceptron, AbstractPerceptron(weights, bias, 0.1) {

}