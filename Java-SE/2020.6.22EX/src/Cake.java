public abstract class Cake {

  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  private double price;


  public Cake(int n, double r) {
    id = n;
    price = r;
  }

  public abstract double calcPrice();

  public String toString() {
    return id + "\t" + price;
  }

  public abstract String printType();
}