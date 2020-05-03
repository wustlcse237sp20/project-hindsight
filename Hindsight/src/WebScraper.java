import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebScraper {
    private String urlPath = "https://markets.businessinsider.com/stocks/";
    public String[] stocks_ticker = {"aapl", "ba", "gs", "axp", "mmm","tsla","nflx"};
    public String[] stocks_name = {"Apple", "Boeing", "Goldman Sachs", "American Express", "3M","Tesla", "Netflix"};
    private String urlEnd = "-stock";
    public WebScraper() {


    }

    public void scrapeWebsite() {
        CSVManager csvManager = new CSVManager();
        for (int i = 0; i < stocks_ticker.length; i++) {
            String url = urlPath + stocks_ticker[i] + urlEnd;
            System.out.print(stocks_ticker[i]);
            try {
                final Document document = Jsoup.connect(url).get();
                final Element element = (checkHTMLTag() ? document.selectFirst("span.push-data.aktien-big-font.text-nowrap.no-padding-at-all") : document.selectFirst("span.big-font-small.text-nowrap.premarket-font"));
                String price = element.text();
                csvManager.writeToFile(price,stocks_name[i]);
               // csvManager.readFile(stocks_name[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        csvManager.initialization();
    }
    public boolean checkHTMLTag() throws ParseException {
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;
        SimpleDateFormat currentDay = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        String day = currentDay.format(date);
        dateFormat.format(date);
        System.out.println(day);
        if(day.equals("Saturday") || day.equals("Sunday")){
            return true;
        }

        if(dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("8:30")) && (dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("15:00"))))
        {
            return true;
        }else{
            return false;
        }
    }
}
