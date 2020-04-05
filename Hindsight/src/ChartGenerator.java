import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartGenerator {
    final private String filePath = "Hindsight/docs/apple.csv";
    public ChartGenerator() {
    }

    public void generateChart(){
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Stock Price");

        LineChart lineChart = new LineChart(xAxis, yAxis);
        XYChart.Series dataSeries = new XYChart.Series();
        
    }
}
