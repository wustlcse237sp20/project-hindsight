import org.junit.Assert;

public class ChartGeneratorTest {

    DatapointCollector collector = new DatapointCollector();
    ChartGenerator chartGenerator = new ChartGenerator();

    @org.junit.jupiter.api.Test
    void testZeroPrice() {
        for (int i = 10; i >= 0; i--) {
            String[] point = {"test", Integer.toString(i)};
            collector.addPoint(point);
        }
        double maxProfit = chartGenerator.caluclateMaxProfit(collector);
        Assert.assertEquals(0, maxProfit, 0);
    }

    @org.junit.jupiter.api.Test
    void testProfitEqualsFifty(){
        for (int i = 0; i <= 10; i++) {
            String[] point = {"test" + i, Integer.toString(5 * i)};
            collector.addPoint(point);
        }
        double maxProfit = chartGenerator.caluclateMaxProfit(collector);
        Assert.assertEquals(50, maxProfit, 0);
    }


}
