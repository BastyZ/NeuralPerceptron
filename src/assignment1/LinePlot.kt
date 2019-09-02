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
    var y2Data: DoubleArray? = null

    fun draw() {
        if (xData == null) xData = DoubleArray(yData!!.size) { i -> i.toDouble()}
        assert(xData!!.size == yData!!.size)
        SwingWrapper(plot).displayChart()
        print("success points ${yData!!.size} error points ${y2Data!!.size}")
        val line = plot.addSeries(seriesName, xData, yData)
        val line2 = plot.addSeries("error", xData, y2Data)
        line2.marker = SeriesMarkers.NONE
        line.marker = SeriesMarkers.NONE
    }
}