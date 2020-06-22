public class OrderCake extends Cake{
    private double weightInKG;

    public double getWeightInKG() {
        return weightInKG;
    }

    public void setWeightInKG(double weightInKG) {
        this.weightInKG = weightInKG;
    }

    public OrderCake(int n, double r, double weightInKG) {
        super(n, r);
        this.weightInKG = weightInKG;
    }

    @Override
    public double calcPrice() {
        return weightInKG * getPrice();
    }

    public String toString() {
        return getId() + "\t" + getPrice() + "\t" + weightInKG + "(OrderCake)";
    }

    public String printType() {
        return "按斤卖";
    }
}
