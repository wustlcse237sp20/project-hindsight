import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {
    private String urlPath = "https://markets.businessinsider.com/stocks/aapl-stock";

    public WebScraper() {

    }

    public void scrapeWebsite(){
        try{
            final Document document = Jsoup.connect(urlPath).get();
            final Element element = document.selectFirst("span.big-font-small.text-nowrap.premarket-font");
            System.out.println(element.text());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
