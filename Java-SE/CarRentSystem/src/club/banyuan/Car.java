package club.banyuan;


import java.io.Serializable;

public class Car extends Vehicle implements Serializable {
    private int seatNum;

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

//    public void setSeatNum(String seatNum) {
//        this.seatNum = seatNum;
//    }

    @Override
    public String toString() {
        return "Car {" +
                "汽车编号:" + super.getId() + '\n' +
                "生产年份:" + super.getAppearanceYear() + '\n' +
                "厂家:" + super.getFactory() + '\n' +
                "保养日期:" + super.getLastMaintenanceTime() + '\n' +
                '}';
    }
}
