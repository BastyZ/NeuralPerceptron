package assignment1

import LinePlot
import assignment1.NeuralNetwork.NeuralNetwork
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

enum class Iris {
    SETOSA {
        override fun encode(): List<Double> = listOf(1.0, .0, .0) },
    VERSICOLOR {
        override fun encode(): List<Double> = listOf(.0, 1.0, .0) },
    VIRGINICA {
        override fun encode(): List<Double> = listOf(.0, .0, 1.0) }
    ;
    abstract fun encode(): List<Double>
}

fun hotEncode(string: String): List<Double> {
    return when(string) {
        "Iris-setosa"    -> Iris.SETOSA.encode()
        "Iris-versicolor"-> Iris.VERSICOLOR.encode()
        "Iris-virginica" -> Iris.VIRGINICA.encode()
        else -> {
            println("Encountered: $string")
            throw Exception("Unexpected name to encode")
        }
    }
}

class Homework(private val trainingRounds: Int = 10000,
               private val trainSubset: Double,
               filename: String
                ) {
    private val network = NeuralNetwork(4,3, listOf(10,10,10,10,10,10))
    private val plot = LinePlot("successRate", "Training Rounds", "Success (%)")
    private var samples = mutableListOf<NeuralNetwork.TrainSample>()

    init {
        var reader: BufferedReader? = null
        try {
            // Read the file
            reader = File(filename).bufferedReader()
            val lines = mutableListOf<String>()
            reader.useLines { line -> line.forEach { lines.add(it) } }

            // shuffle the lines to add randomness to the training set
            lines.shuffle()

            // set maximums and minimums to normalize after
            lines.forEach { line ->
                if (line != "") {
                    val lineArray = line.split(",")
                    line.slice(0 until (lineArray.size -1)).withIndex().forEach {
                        NeuralNetwork.setMinMax(it.value.toDouble(), it.index)
                    }
                }
            }

            // Extract values to objects
            lines.forEach { line ->
                val lineArray = line.split(",")
                if (line != "") {
                    val ans = hotEncode(lineArray.last())

                    // Add all the elements (normalized) as Double
                    val inputs = mutableListOf<Double>()
                    line.slice(0 until (lineArray.size -1)).withIndex().forEach {
                        inputs.add( NeuralNetwork.normalize(it.value.toDouble(), it.index) * 4 )
                    }

                    // add to the samples
                    samples.add(NeuralNetwork.TrainSample(inputs, ans))
                }
            }

            // we have to split the sets to test and train
            val sampleSize = samples.size
            val trainSample: MutableList<NeuralNetwork.TrainSample> =
                samples.slice(0 until (sampleSize*trainSubset).toInt()).toMutableList()
            val testSample: MutableList<NeuralNetwork.TrainSample> =
                samples.slice((sampleSize*trainSubset).toInt() until sampleSize).toMutableList()

            // Set samples and train
            network.setSamples(trainSample, testSample)
            network.trainRounds(trainingRounds)

        } catch (error: FileNotFoundException) {
            println("File Not Found")
            println(error.message)
        } catch (error: Exception) {
            println(error.message)
        } finally {
            reader?.close()
            this.draw()
        }
    }

    fun draw() {
        plot.yData = network.successRate.toDoubleArray()
        plot.y2Data = network.errorRate.toDoubleArray()
        plot.draw()
    }
}

fun main() {
    // trainsubset es la cantidad de set que será usado para entrenar
    // usar valor entre 0 y 1
    val work = Homework(10000, .3,"iris.data")
}