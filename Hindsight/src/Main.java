import com.opencsv.CSVReader;

import java.io.FileReader;

public class Main {
    public static void main(String[] args){
        CSVManager csvManager = new CSVManager();
        csvManager.readFile();
        WebScraper webScraper = new WebScraper();
        webScraper.scrapeWebsite();
    }
}
