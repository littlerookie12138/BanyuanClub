public class ReadyMadeCake extends Cake{
    private int quantity;

    public ReadyMadeCake(int n, double r, int quantity) {
        super(n, r);
        this.quantity = quantity;
    }

    @Override
    public double calcPrice() {
        return quantity * getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return getId() + "\t" + getPrice() + "\t" + quantity + "(ReadyMadeCake)";
    }

    public String printType() {
        return "按个卖";
    }
}
