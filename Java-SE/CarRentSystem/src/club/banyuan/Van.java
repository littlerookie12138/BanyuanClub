package club.banyuan;

public class Van extends Vehicle{
    private String fuelType;

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Van {" +
                 "汽车编号:" + super.getId() + '\n' +
                "生产年份:" + super.getAppearanceYear() + '\n' +
                "厂家:" + super.getFactory() + '\n' +
                "保养日期:" + super.getLastMaintenanceTime() + '\n' +
                '}';
    }
}
