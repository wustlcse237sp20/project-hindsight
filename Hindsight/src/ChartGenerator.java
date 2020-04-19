import com.sun.xml.internal.bind.v2.TODO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChartGenerator {
    final private String filePath = "Hindsight/docs/apple.csv";
    private Stage stage;
    XYChart.Series dataSeries;

    public ChartGenerator(){
        dataSeries = new XYChart.Series();
    }

    public ChartGenerator(Stage stage) {
        dataSeries = new XYChart.Series();
        this.stage = stage;
    }


    public void generateChart(DatapointCollector collector){
        stage.setTitle("Apple Price Tracker");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Date");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Stock Price");

        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        dataSeries.setName("Prices");
        double maxProfit = caluclateMaxProfit(collector);
        System.out.println(maxProfit);
        lineChart.getData().add(dataSeries);

        StackPane layout = new StackPane();
        layout.getChildren().add(lineChart);

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public double caluclateMaxProfit(DatapointCollector collector){
        int counter = 0;
        double runningMaxProfit = 0;
        double minPrice = Integer.MAX_VALUE;
        String[] buyingPoint = {};
        String[] sellingPoint = {};
        for(String[] dataPoint: collector.getListPoints()){
            if(dataPoint.length != 2){
                continue;
            }
            double stockPrice = Double.parseDouble(dataPoint[1]);
            if(stockPrice < minPrice){
                buyingPoint = dataPoint;
                minPrice = stockPrice;
            }
            if(stockPrice - minPrice > runningMaxProfit){
                sellingPoint = dataPoint;
                runningMaxProfit = stockPrice - minPrice;
            }
            dataSeries.getData().add(new XYChart.Data<>(counter, stockPrice));
            counter++;
        }
        showDialogBox(buyingPoint, sellingPoint);
        return runningMaxProfit;
    }

    public void showDialogBox(String[] buyingPoint, String[] sellingPoint){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Buying Summary");
        alert.setHeaderText("Optimal Strategy for Apple");
        alert.setContentText("Purchase Date: " + buyingPoint[0] + " @ $" + buyingPoint[1] + "\nSell Date: " + sellingPoint[0] + " @ $" + sellingPoint[1]);

        alert.showAndWait();
    }


}
