import com.opencsv.CSVReader;

import java.io.FileReader;

public class Main {
    public static void main(String[] args){
        WebScraper webScraper = new WebScraper();
        webScraper.scrapeWebsite();
    }
}
