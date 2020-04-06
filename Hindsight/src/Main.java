import com.opencsv.CSVReader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileReader;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WebScraper webScraper = new WebScraper();
        webScraper.scrapeWebsite();
    }
}
