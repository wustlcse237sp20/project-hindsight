import javafx.scene.chart.XYChart;
import org.junit.Assert;

import java.text.ParseException;
import java.util.Date;

public class ChartGeneratorTest {

    DatapointCollector collector = new DatapointCollector();
    ChartGenerator chartGenerator = new ChartGenerator();
    XYChart.Series<Date, Number> dataSeries = new XYChart.Series<>();

    @org.junit.jupiter.api.Test
    void testZeroPrice() throws ParseException {
        for (int i = 10; i >= 0; i--) {
            String[] point = {"2020-01-01", Integer.toString(i)};
            collector.addPoint(point);
        }
        DialogBoxData maxProfit = collector.calculateMaxProfit(dataSeries);
        Assert.assertEquals(0, maxProfit.getMaxProfit(), 0);
    }

    @org.junit.jupiter.api.Test
    void testProfitEqualsFifty() throws ParseException {
        for (int i = 0; i <= 10; i++) {
            String[] point = {"2020-01-01" + i, Integer.toString(5 * i)};
            collector.addPoint(point);
        }
        DialogBoxData maxProfit = collector.calculateMaxProfit(dataSeries);
        Assert.assertEquals(50, maxProfit.getMaxProfit(), 0);
    }

    @org.junit.jupiter.api.Test
    void testProfitEqualsHundred() throws ParseException {
        for(int i = 1; i<=10; i++){
            String[] point;
            if (i == 3){
                point = new String[]{"2020-01-01", Integer.toString(0)};
            }
            else if (i==7){
                point = new String[]{"2020-01-01", Integer.toString(100)};
            }
            else{
                point = new String[]{"2020-01-01", Integer.toString(i)};
            }
            collector.addPoint(point);
        }
        try{
            DialogBoxData maxProfit = collector.calculateMaxProfit(dataSeries);
            Assert.assertEquals(100, maxProfit.getMaxProfit(), 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
