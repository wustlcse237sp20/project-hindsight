import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebScrapingTest {
    WebScraper scraper = new WebScraper();

    @org.junit.jupiter.api.Test
    void testMarketsOpen() throws ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;
        boolean isMarketHours = dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("8:30")) && (dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("15:00")));
        boolean scraperMarketHours = scraper.checkMarketsOpen();
        Assert.assertEquals(isMarketHours, scraperMarketHours);
    }
}
