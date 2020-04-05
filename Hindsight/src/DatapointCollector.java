import java.util.ArrayList;
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
            System.out.println(point[0] + ": " + point[1]);
        }
    }

    public List<String[]> getListPoints() {
        return listPoints;
    }
}
