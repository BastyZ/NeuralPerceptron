import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.style.markers.SeriesMarkers

/**
 * Class to create the plot, used to show the results of training
 * @author Ignacio Slater Muñoz
 */
class LinePlot(title: String, xLabel: String, yLabel: String, private val seriesName: String = "Success rate") {
    private val plot = XYChartBuilder().width(800).height(600).title(title).xAxisTitle(xLabel).yAxisTitle(yLabel).build()
    var yData: DoubleArray? = null
    var xData: DoubleArray? = null

    fun draw() {
        if (xData == null) xData = DoubleArray(yData!!.size) { i -> i.toDouble()}
        assert(xData!!.size == yData!!.size)
        SwingWrapper(plot).displayChart()
        val line = plot.addSeries(seriesName, xData, yData)
        line.marker = SeriesMarkers.NONE
    }
}