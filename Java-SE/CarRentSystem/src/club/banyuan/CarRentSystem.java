package club.banyuan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class CarRentSystem {
    /**
     * 1.添加汽车
     * 2.出租汽车
     * 3.归还汽车
     * 4.汽车保养
     * 5.结束保养
     * 6.查询信息
     * 7.退出系统
     */
    private List<Vehicle> vanList = new ArrayList<>();
    private List<Vehicle> carList = new ArrayList<>();

    public List<Vehicle> getVanList() {
        return vanList;
    }


    public List<Vehicle> getCarList() {
        return carList;
    }

    public void addCar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入添加的车型:");
        System.out.println("Van:卡车");
        System.out.println("Car:汽车");
        System.out.print("请输入:");
        String choice = sc.nextLine();

        switch (choice) {
            case "Van":
            case "卡车":
                // TODO: 2020/7/4 对输入进行检查
                Vehicle van = new Van();
                System.out.println("请填写出场年份:");
                int date = sc.nextInt();
                van.setAppearanceYear(date);

                System.out.println("请填写车子的出厂厂家:");
                String factory = sc.next();
                van.setFactory(factory);

                // TODO: 2020/7/4 对输入进行检查
                System.out.println("请填写车子的编号");
                System.out.print("车辆编号:V_");
                long carID = sc.nextLong();
                van.setId(carID);

                // TODO: 2020/7/4
                System.out.println("请选择燃油类型:");
                System.out.println("1、汽油");
                System.out.println("2、燃油");
                int fuelType = sc.nextInt();
                ((Van) van).setFuelType((fuelType == 1 ? "汽油" : "燃油"));

                // TODO: 2020/7/4  对输入进行检查
                System.out.println("请填写最后的保养日期:");
                System.out.print("保养日期(年-月-日)");
                String maintenanceDate = sc.next();
                van.setLastMaintenanceTime(maintenanceDate);

                System.out.println(van);
                vanList.add(van);
                break;
            case "汽车":
            case "Car":
                // TODO: 2020/7/4 对输入进行检查
                Vehicle car = new Car();
                System.out.println("请填写出场年份:");
                int date1 = sc.nextInt();
                car.setAppearanceYear(date1);

                System.out.println("请填写车子的出厂厂家:");
                String factory1 = sc.next();
                car.setFactory(factory1);

                // TODO: 2020/7/4 对输入进行检查
                System.out.println("请填写车子的编号");
                System.out.print("车辆编号:C_");
                long carID1 = sc.nextLong();
                car.setId(carID1);

                // TODO: 2020/7/4
//                String input = getInput("请输入轿车座位数量(4/7)\n", s -> {
//                    try {
//                        int i = Integer.parseInt(s);
//                        return i >= 4 && i <= 7;
//                    } catch (NumberFormatException e) {
//                        return false;
//                    }
//                });
                String input = getInput("请输入轿车座位数量(4/7)\n", new PredictNumbers(4, 7));
//                System.out.println("请输入轿车座位数量(4/7)");
//                int seatNum = sc.nextInt();
//                ((Car) car).setSeatNum(seatNum);
                ((Car) car).setSeatNum(input);

                // TODO: 2020/7/4  对输入进行检查
                System.out.println("请填写最后的保养日期:");
                System.out.print("保养日期(年-月-日)");

                getInput("请填写最后的保养日期:", s -> s.matches("\\d{4}-\\d{2}-\\d{2}"));
                String maintenanceDate1 = sc.next();
                car.setLastMaintenanceTime(maintenanceDate1);

                System.out.println(car);
                carList.add(car);
                break;
        }
    }


    public String getInput(String title, Predicate<String> judge) {
        System.out.println(title);
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        boolean isIllegal = judge.test(s);
        if (isIllegal) {
            return getInput(title, judge);
        } else {
            return s;
        }
    }

//    public String getInput(String title, Judge judge) {
//        System.out.print(title);
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
//        boolean isIllegal = judge.judge(s);
//        if (isIllegal) {
//            return getInput(title, judge);
//        } else {
//            return s;
//        }
//    }
//
//    public String getInput(Judge judge) {
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
//        boolean isIllegal = judge.judge(s);
//        if (isIllegal) {
//            return getInput(judge);
//        } else {
//            return s;
//        }
//    }

    public void rentVehicle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要租借的汽车编号:");
        System.out.print("汽车编号:");
        String rentId = sc.next();
        String[] rentType = rentId.split("_");
        switch (rentType[0]) {
            case "V":
                //要租卡车
                System.out.println("卡车");
                break;
            case "C":
                //要租汽车
                System.out.println("汽车");
                break;
        }
    }
}
