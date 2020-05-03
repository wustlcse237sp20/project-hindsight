import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import com.opencsv.CSVReader;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChartGenerator {
    final private String filePath = "apple.csv";
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

        DateAxis xAxis = new DateAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Stock Price");
        Button button1 = new Button("back to main page");
        CSVManager csvManager = new CSVManager();
        button1.setOnAction(e->csvManager.initialization());
        LineChart<Date, Number> lineChart = new LineChart<Date, Number>(xAxis, yAxis);
        dataSeries.setName("Prices");
        DialogBoxData dialogBoxData = collector.calculateMaxProfit(dataSeries);
        showDialogBox(dialogBoxData,stock);
        
	lineChart.getData().add(dataSeries);
        VBox vbox = new VBox(20);
        StackPane layout = new StackPane();
        StackPane chartPane = new StackPane();
        StackPane buttonPane = new StackPane();
        buttonPane.getChildren().add(button1);
        chartPane.getChildren().add(lineChart);
        vbox.getChildren().addAll(chartPane,buttonPane);
        Scene scene = new Scene(vbox, 750, 500);
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


    public void showDialogBox(DialogBoxData dialogBoxData, String stock){
        double profit = Math.floor(dialogBoxData.getMaxProfit() * 100) / 100;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Buying Summary");
        alert.setHeaderText("Optimal Strategy for " + stock);
        alert.setContentText("Purchase Date: " + dialogBoxData.getBuyingPoint()[0]+ " @ $" + dialogBoxData.getBuyingPoint()[1] + "\nSell Date: " + dialogBoxData.getSellingPoint()[0] + " @ $" + dialogBoxData.getSellingPoint()[1] + "\nProfit: $" + profit);
        alert.showAndWait();
    }
}
