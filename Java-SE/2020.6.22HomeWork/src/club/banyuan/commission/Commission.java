package club.banyuan.commission;

public class Commission extends Hourly{
    private double totalSale = 0;
    private double salaryRate;

    public Commission(String eName, String eAddress, String ePhone, String socSecNumber, double rate, double salaryRate) {
        super(eName, eAddress, ePhone, socSecNumber, rate);
        this.salaryRate = salaryRate;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public double getSalaryRate() {
        return salaryRate;
    }

    public void setSalaryRate(double salaryRate) {
        this.salaryRate = salaryRate;
    }

    public void addSales(double totalSale) {
        this.totalSale = totalSale;
    }

    public double pay() {
        double paySalary = totalSale + super.pay();
        totalSale = 0;
        return paySalary;
    }

    public String toString() {
        String result = super.toString();
        result += "\n总销售额: " + totalSale;
        return result;
    }
}
