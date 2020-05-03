import javafx.scene.chart.XYChart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DatapointCollector {
    private List<String[]> listPoints;
    public DatapointCollector() {
        listPoints = new ArrayList<>();
    }

    public void addPoint(String[] point){
        listPoints.add(point);
    }

    public void printPoints(){
        Iterator iterator = listPoints.iterator();
        while (iterator.hasNext()){
            String[] point = (String[]) iterator.next();
            if(point.length != 2){
                continue;
            }
            System.out.println(point[0] + ": " + point[1]);
        }
    }

    public DialogBoxData calculateMaxProfit(XYChart.Series<Date, Number> dataSeries) throws ParseException {
        double runningMaxProfit = 0;
        double minPrice = Integer.MAX_VALUE;
        String[] buyingPoint = {};
        String[] sellingPoint = {};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(String[] dataPoint: this.listPoints){
            if(dataPoint.length != 2){
                continue;
            }
            double stockPrice = Double.parseDouble(dataPoint[1]);
            if(stockPrice < minPrice){
                buyingPoint = dataPoint;
                minPrice = stockPrice;
            }
            if(stockPrice - minPrice >= runningMaxProfit){
                sellingPoint = dataPoint;
                runningMaxProfit = stockPrice - minPrice;
            }

            dataSeries.getData().add(new XYChart.Data<>(dateFormat.parse(dataPoint[0]), stockPrice));
        }
        return new DialogBoxData(runningMaxProfit, buyingPoint, sellingPoint);

    }

    public List<String[]> getListPoints() {
        return listPoints;
    }
}
