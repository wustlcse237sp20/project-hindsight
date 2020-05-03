import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
<<<<<<< HEAD
=======
import javafx.scene.control.Alert;
import com.opencsv.CSVReader;
import javafx.scene.control.Button;
>>>>>>> origin/scrap_multi_page
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.Alert;

public class ChartGenerator {
    private Stage stage;
    XYChart.Series<Date, Number> dataSeries;
<<<<<<< HEAD

=======
>>>>>>> origin/scrap_multi_page
    public ChartGenerator(){
        dataSeries = new XYChart.Series();
    }

    public ChartGenerator(Stage stage) {
        dataSeries = new XYChart.Series();
        this.stage = stage;
    }


    public void generateChart(DatapointCollector collector) throws ParseException {
        stage.setTitle("Apple Price Tracker");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        DateAxis xAxis = new DateAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Stock Price");
        LineChart<Date, Number> lineChart = new LineChart<Date, Number>(xAxis, yAxis);
        dataSeries.setName("Prices");
<<<<<<< HEAD

        DialogBoxData dialogBoxData = collector.calculateMaxProfit(dataSeries);
        showDialogBox(dialogBoxData);
        lineChart.getData().add(dataSeries);

=======
        DialogBoxData dialogBoxData = collector.calculateMaxProfit(dataSeries);
        showDialogBox(dialogBoxData,stock);
        
	lineChart.getData().add(dataSeries);
        VBox vbox = new VBox(20);
>>>>>>> origin/scrap_multi_page
        StackPane layout = new StackPane();
        layout.getChildren().add(lineChart);

        Scene scene = new Scene(layout, 750, 500);
        stage.setScene(scene);


        for(XYChart.Series<Date, Number> s: lineChart.getData()){
            for (XYChart.Data<Date, Number> d : s.getData()){
                Tooltip tooltip = new Tooltip();
                tooltip.setText("Date: " + dateFormat.format(d.getXValue()) + "\n" + "Price: "+ d.getYValue());
                Tooltip.install(d.getNode(),tooltip);
            }
        }
        stage.show();
    }

<<<<<<< HEAD
    public void showDialogBox(DialogBoxData dialogBoxData){
        double profit = Math.floor(dialogBoxData.getMaxProfit() * 100) / 100;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Buying Summary");
        alert.setHeaderText("Optimal Strategy for Apple");
        alert.setContentText("Purchase Date: " + dialogBoxData.getBuyingPoint()[0]+ " @ $" + dialogBoxData.getBuyingPoint()[1] + "\nSell Date: " + dialogBoxData.getSellingPoint()[0] + " @ $" + dialogBoxData.getSellingPoint()[1] + "\nProfit: $" + profit);

=======

    public void showDialogBox(DialogBoxData dialogBoxData, String stock){
        double profit = Math.floor(dialogBoxData.getMaxProfit() * 100) / 100;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Buying Summary");
        alert.setHeaderText("Optimal Strategy for " + stock);
        alert.setContentText("Purchase Date: " + dialogBoxData.getBuyingPoint()[0]+ " @ $" + dialogBoxData.getBuyingPoint()[1] + "\nSell Date: " + dialogBoxData.getSellingPoint()[0] + " @ $" + dialogBoxData.getSellingPoint()[1] + "\nProfit: $" + profit);
>>>>>>> origin/scrap_multi_page
        alert.showAndWait();
    }


}
