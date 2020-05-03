import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVManager {
    private String fileFormat = ".csv";
    private Stage stage;
    public String[] stocksName = {"Apple", "Boeing", "Goldman Sachs", "American Express", "3M","Tesla", "Netflix"};
    private ChartGenerator chartGenerator;

    public CSVManager() {
        this.stage = new Stage();
        this.chartGenerator =  new ChartGenerator(this.stage);
    }
    public void initialization(){
        VBox vbox = new VBox(20);
        StackPane layout = new StackPane();
        for(int i=0; i< stocksName.length; i++) {
            Button button = new Button(stocksName[i]);
            int finalI = i;
            button.setOnAction(e -> readFile(stocksName[finalI]));
            vbox.getChildren().add(button);
        }
        layout.getChildren().add(vbox);
        layout.setAlignment(vbox, Pos.CENTER);
        this.stage.setScene(new Scene(layout, 750, 500));
        this.stage.setTitle("Stock Price Monitor");
        this.stage.show();
    }

    public void readFile(String stock){
        try{
            CSVReader csvReader = new CSVReader(new FileReader(stock+fileFormat));
            DatapointCollector collector = new DatapointCollector();
            String[] nextEntry;
            while((nextEntry = csvReader.readNext()) != null){
                collector.addPoint(nextEntry);
            }
            this.chartGenerator.generateChart(collector, stock);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeToFile(String price, String stock){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        try{
            File file = new File(stock+fileFormat);
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file, true));
            String[] newEntry = {currentDate, price};
            csvWriter.writeNext(newEntry);
            csvWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
