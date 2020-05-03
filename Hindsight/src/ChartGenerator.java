import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChartGenerator {
    //final private String filePath = "Hindsight/src/appl.csv";
    private Stage stage;
    XYChart.Series<Date, Number> dataSeries;
    Scene[] scenes = new Scene[5];
    public ChartGenerator(){
        dataSeries = new XYChart.Series();
    }

    public ChartGenerator(Stage stage) {
        dataSeries = new XYChart.Series();
        this.stage = stage;
    }

    public void generateChart(DatapointCollector collector, String stock) throws ParseException {
        stage.setTitle(stock + " Price Tracker");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        extfx.scene.chart.DateAxis xAxis = new extfx.scene.chart.DateAxis();
        xAxis.setLabel("Date");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Stock Price");
        Button button1 = new Button("next");
        LineChart<Date, Number> lineChart = new LineChart<Date, Number>(xAxis, yAxis);
        dataSeries.setName("Prices");
        double maxProfit = caluclateMaxProfit(collector, stock);
        System.out.println(maxProfit);
        lineChart.getData().add(dataSeries);
        StackPane layout = new StackPane();
        layout.getChildren().add(button1);
        layout.getChildren().add(lineChart);
        Scene scene = new Scene(layout, 750, 500);
        stage.setScene(scene);

        for (XYChart.Series<Date, Number> s : lineChart.getData()) {
            for (XYChart.Data<Date, Number> d : s.getData()){
                Tooltip tooltip = new Tooltip();
                tooltip.setText("Date: " + dateFormat.format(d.getXValue()) + "\n" + "Price: "+ d.getYValue());
                Tooltip.install(d.getNode(),tooltip);
            }
        }
        stage.show();
    }

    public double caluclateMaxProfit(DatapointCollector collector, String stock) throws ParseException {
        int counter = 0;
        double runningMaxProfit = 0;
        double minPrice = Integer.MAX_VALUE;
        String[] buyingPoint = {};
        String[] sellingPoint = {};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(String[] dataPoint: collector.getListPoints()){
            if(dataPoint.length != 2){
                continue;
            }
            double stockPrice = Double.parseDouble(dataPoint[1]);
            if(stockPrice < minPrice){
                buyingPoint = dataPoint;
                minPrice = stockPrice;
            }
            if(stockPrice - minPrice >= runningMaxProfit){
                sellingPoint = dataPoint;
                runningMaxProfit = stockPrice - minPrice;
            }

            dataSeries.getData().add(new XYChart.Data<Date, Number>(dateFormat.parse(dataPoint[0]), stockPrice));
            System.out.println(stockPrice);
            counter++;
        }
        showDialogBox(buyingPoint, sellingPoint, runningMaxProfit, stock);
        return runningMaxProfit;
    }

    public void showDialogBox(String[] buyingPoint, String[] sellingPoint, double profit, String stock){
        profit = Math.floor(profit * 100) / 100;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Buying Summary");
        alert.setHeaderText("Optimal Strategy for " + stock);
        System.out.println(buyingPoint[0]);
        System.out.println(buyingPoint[1]);
        System.out.println(sellingPoint[0]);
        System.out.println(sellingPoint[1]);
        alert.setContentText("Purchase Date: " + buyingPoint[0] + " @ $" + buyingPoint[1] + "\nSell Date: " + sellingPoint[0] + " @ $" + sellingPoint[1] + "\n$Profit: " + profit);
        alert.showAndWait();
    }


}
