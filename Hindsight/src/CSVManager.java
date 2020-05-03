import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVManager {
    private String path = "Hindsight/src/";
    private String fileFormat = ".csv";
    private Stage stage;
    private ChartGenerator chartGenerator;
    public CSVManager() {
        this.stage = new Stage();
        this.chartGenerator =  new ChartGenerator(this.stage);
    }
    public void initialization(){
        VBox layout = new VBox(20);
        Button button1 = new Button("Apple");
        button1.setOnAction(e -> readFile("Apple"));

        Button button2 = new Button("Boeing");
        button2.setOnAction(e -> readFile("Boeing"));

        Button button3 = new Button("Goldman Sachs");
        button3.setOnAction(e -> readFile("Goldman Sachs"));

        Button button4 = new Button("American Express");
        button4.setOnAction(e -> readFile("American Express"));

        Button button5 = new Button("3M");
        button5.setOnAction(e -> readFile("3M"));
        Scene scene = new Scene(layout, 750, 500);
        layout.getChildren().addAll(button1,button2,button3, button4,button5);
        this.stage.setScene(scene);
        this.stage.setTitle("Stock Price Monitor");
        this.stage.show();
    }

    public void readFile(String stock){
        try{
            CSVReader csvReader = new CSVReader(new FileReader(path+stock+fileFormat));
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
            File file = new File(path+stock+fileFormat);
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file, true));
            String[] newEntry = {currentDate, price};
            csvWriter.writeNext(newEntry);
            csvWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
