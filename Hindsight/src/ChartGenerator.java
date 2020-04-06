import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChartGenerator {
    final private String filePath = "Hindsight/docs/apple.csv";
    private Stage stage;

    public ChartGenerator(Stage stage) {
        this.stage = stage;
    }


    public void generateChart(DatapointCollector collector){
        stage.setTitle("Apple Price Tracker");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Date");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Stock Price");

        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Prices");
        int counter = 1;
        for(String[] dataPoint: collector.getListPoints()){
            dataSeries.getData().add(new XYChart.Data<>(counter, Double.parseDouble(dataPoint[1])));
            counter++;
        }
        lineChart.getData().add(dataSeries);

        StackPane layout = new StackPane();
        layout.getChildren().add(lineChart);

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
