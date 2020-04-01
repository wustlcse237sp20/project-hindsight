import com.opencsv.CSVReader;
import java.io.FileReader;

public class CSVManager {
    private String path = "Hindsight/docs/apple.csv";

    public CSVManager() {

    }

    public void readFile(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader(path));
            String[] nextEntry;
            while((nextEntry = csvReader.readNext()) != null){
                for(String s: nextEntry){
                    System.out.print(s + " ");
                }
                System.out.println();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
