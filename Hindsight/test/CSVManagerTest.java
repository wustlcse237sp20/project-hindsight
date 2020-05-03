import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Assert;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVManagerTest {
    String path = "apple.csv";
    String samplePath = "apple_test.csv";
    String samplePrice = "100";

    @org.junit.jupiter.api.Test
    void testReadPriceNumPoints() throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new FileReader(path));
        DatapointCollector collector = new DatapointCollector();
        String[] nextEntry;
        int numRows = 0;
        while((nextEntry = csvReader.readNext()) != null){
            numRows++;
            collector.addPoint(nextEntry);
        }
        Assert.assertEquals(numRows, collector.getListPoints().size());
    }

    @org.junit.jupiter.api.Test
    void testWritePrice(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        try{
            File file = new File(path);
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file, true));
            String[] newEntry = {currentDate, samplePrice};
            csvWriter.writeNext(newEntry);
            csvWriter.close();

            CSVReader csvReader = new CSVReader(new FileReader(path));
            DatapointCollector collector = new DatapointCollector();
            String[] nextEntry;
            String lastPrice = "";
            while((nextEntry = csvReader.readNext()) != null){
                lastPrice = nextEntry[1];
            }
            Assert.assertEquals(samplePrice, lastPrice);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
