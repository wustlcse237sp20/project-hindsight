import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVManager {
    private String path = "apple.csv";

    public CSVManager() {

    }

    public void readFile(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader(path));
            DatapointCollector collector = new DatapointCollector();
            String[] nextEntry;
            while((nextEntry = csvReader.readNext()) != null){
                collector.addPoint(nextEntry);
            }
            Stage stage = new Stage();
            ChartGenerator chartGenerator = new ChartGenerator(stage);
            chartGenerator.generateChart(collector);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeToFile(String price){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        try{
            File file = new File(path);
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file, true));
            String[] newEntry = {currentDate, price};
            csvWriter.writeNext(newEntry);
            csvWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
