public class DialogBoxData {
    private double maxProfit;
    private String[] buyingPoint;
    private String[] sellingPoint;

    public DialogBoxData() {

    }

    public DialogBoxData(double maxProfit, String[] buyingPoint, String[] sellingPoint) {
        this.maxProfit = maxProfit;
        this.buyingPoint = buyingPoint;
        this.sellingPoint = sellingPoint;
    }



    public double getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(double maxProfit) {
        this.maxProfit = maxProfit;
    }

    public String[] getBuyingPoint() {
        return buyingPoint;
    }

    public void setBuyingPoint(String[] buyingPoint) {
        this.buyingPoint = buyingPoint;
    }

    public String[] getSellingPoint() {
        return sellingPoint;
    }

    public void setSellingPoint(String[] sellingPoint) {
        this.sellingPoint = sellingPoint;
    }
}
