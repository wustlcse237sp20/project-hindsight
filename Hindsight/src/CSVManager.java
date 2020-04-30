import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVManager {
    private String path = "Hindsight/src/";
    private String fileFormat = ".csv";
    public CSVManager() {

    }

    public void readFile(String stock){
        try{
            CSVReader csvReader = new CSVReader(new FileReader(path+stock+fileFormat));
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
