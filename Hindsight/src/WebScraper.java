import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebScraper {
    private String urlPath = "https://markets.businessinsider.com/stocks/aapl-stock";

    public WebScraper() {

    }

    public void scrapeWebsite(){
        try{
            final Document document = Jsoup.connect(urlPath).get();
            final Element element = (checkMarketsOpen() ? document.selectFirst("span.push-data.aktien-big-font.text-nowrap.no-padding-at-all"): document.selectFirst("span.big-font-small.text-nowrap.premarket-font"));
            String price = element.text();
            CSVManager csvManager = new CSVManager();
            csvManager.writeToFile(price);
            csvManager.readFile();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean checkMarketsOpen() throws ParseException {
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;
        dateFormat.format(date);

        if(dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("8:30")) && (dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("15:00"))))
        {
            return true;
        }else{
            return false;
        }
    }
}