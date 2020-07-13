package club.banyuan;

public class Vehicle {
    private String type;
    private int appearanceYear;
    private String factory;
    private String id;
    private String lastMaintenanceTime;
    private boolean isRented = false;

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public boolean isRented() {
        return isRented;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAppearanceYear() {
        return appearanceYear;
    }

    public void setAppearanceYear(int appearanceYear) {
        this.appearanceYear = appearanceYear;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastMaintenanceTime() {
        return lastMaintenanceTime;
    }

    public void setLastMaintenanceTime(String lastMaintenanceTime) {
        this.lastMaintenanceTime = lastMaintenanceTime;
    }
}
