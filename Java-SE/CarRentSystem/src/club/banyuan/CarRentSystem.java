package club.banyuan;

import com.alibaba.fastjson.JSON;

import java.io.*;
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
    private static final String VANPATH = ProUtil.getVanPth();
    private static final String CARPATH = ProUtil.getCarPath();

    public List<Vehicle> getVanList() {
        return vanList;
    }

    public List<Vehicle> getCarList() {
        return carList;
    }

    //存储van
    public void storeVan() throws IOException {
        File file = new File(VANPATH);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                //父类目录不存在
                //创建父类
                file.getParentFile().mkdirs();
            } else {
                file.createNewFile();
            }
        }

        try (FileOutputStream objectOutputStream = new FileOutputStream(file);){
            objectOutputStream.write(vanList.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //存储car
    public void storeCar() throws IOException {
        File file = new File(CARPATH);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                //父类目录不存在
                //创建父类
                file.getParentFile().mkdirs();
            } else {
                file.createNewFile();
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);){
            fileOutputStream.write(carList.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addVehicle() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入添加的车型:");
        System.out.println("Van:卡车");
        System.out.println("Car:汽车");
        System.out.print("请输入:");
        String choice = sc.nextLine();

        switch (choice) {
            case "Van":
            case "卡车":
                addVan(sc);
                storeVan();
                break;
            case "汽车":
            case "Car":
                addCar(sc);
                storeCar();
                break;
        }
    }

    private void addCar(Scanner sc) {
        // TODO: 2020/7/4 对输入进行检查
        Vehicle car = new Car();
        System.out.println("请填写出场年份:");
        int date1 = sc.nextInt();
        car.setAppearanceYear(date1);

        System.out.println("请填写车子的出厂厂家:");
        String factory = sc.next();
        car.setFactory(factory);

        // TODO: 2020/7/4 对输入进行检查
        System.out.println("请填写车子的编号");
        System.out.print("车辆编号:C_");
        String carID = sc.next();
        car.setId("C_" + carID);

        // TODO: 2020/7/4
        String input = getInput("请输入轿车座位数量(4/7)\n", new PredictNumbers(4, 7));
        ((Car) car).setSeatNum(Integer.parseInt(input));

        // TODO: 2020/7/4  对输入进行检查
//        System.out.println("请填写最后的保养日期:");
        getInput("请填写最后的保养日期:", s -> s.matches("\\d{4}-\\d{2}-\\d{2}"));
        System.out.print("保养日期(年-月-日)");
        String maintenanceDate1 = sc.next();
        car.setLastMaintenanceTime(maintenanceDate1);

        System.out.println(car);
        carList.add(car);
    }

    private void addVan(Scanner sc) {
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
        String carID = sc.next();
        van.setId("C_" + carID);

        // TODO: 2020/7/4
        System.out.println("请选择燃油类型:");
        System.out.println("1、汽油");
        System.out.println("2、燃油");
        int fuelType = sc.nextInt();
        ((Van) van).setFuelType((fuelType == 1 ? "汽油" : "燃油"));

        // TODO: 2020/7/4  对输入进行检查
        getInput("请填写最后的保养日期:", s -> s.matches("\\d{4}-\\d{2}-\\d{2}"));
        System.out.print("保养日期(年-月-日)");
        String maintenanceDate = sc.next();
        van.setLastMaintenanceTime(maintenanceDate);

        System.out.println(van);
        vanList.add(van);
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

    public void rentVehicle() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要租借的汽车编号:");
        System.out.print("汽车编号:");
        String rentId = sc.next();
        String[] rentType = rentId.split("_");
        switch (rentType[0]) {
            case "V":
                //要租卡车
                if (!rentVan(sc, rentId)) {
                    System.out.println("抱歉，查无此车或者该车被租走了!");
                }
                storeVan();
                break;
            case "C":
                //要租汽车
                if (!rentCar(sc, rentId)) {
                    System.out.println("抱歉，查无此车或者该车被租走了!");
                }
                storeCar();
                break;
        }
    }

    public void returnVehicle() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要归还的汽车编号:");
        System.out.print("汽车编号:");
        String returnID = sc.next();
        String[] returnType = returnID.split("_");
        switch (returnType[0]) {
            case "V":
                if (!returnVan(sc, returnID)) {
                    System.out.println("该车未被借出或查无此车");
                }
                storeVan();
                break;
            case "C":
                if (!returnCar(sc, returnID)) {
                    System.out.println("该车未被借出或查无此车");
                }
                storeCar();
                break;
        }
    }

    private boolean returnVan(Scanner sc, String returnID) {
        for (Vehicle vehicle : vanList) {
            if (vehicle.getId().equals(returnID)) {
                if (!vehicle.isRented()) {
                    //车没被借出去
                    return false;
                }
                System.out.println("请填写归还日期:");
                System.out.print("归还日期(年-月-日):");
                String returnDate = sc.next();

                vehicle.setRented(false);
                System.out.println(vehicle.getId() + "已被成功归还");
                return true;
            }
        }

        return false;
    }

    private boolean returnCar(Scanner sc, String returnID) {
        for (Vehicle vehicle : carList) {
            if (vehicle.getId().equals(returnID)) {
                if (!vehicle.isRented()) {
                    //车没被借出去
                    return false;
                }
                System.out.println("请填写归还日期:");
                System.out.print("归还日期(年-月-日):");
                String returnDate = sc.next();

                vehicle.setRented(false);
                System.out.println(vehicle.getId() + "已被成功归还");
                return true;
            }
        }

        return false;
    }


    private boolean rentCar(Scanner sc, String rentId) {
        for (Vehicle vehicle : carList) {
            if (vehicle.getId().equals(rentId)) {
                if (vehicle.isRented()) {
                    return false;
                }
                System.out.println("请填写您的顾客编号:");
                System.out.print("顾客编号:");
                int customerID = sc.nextInt();

                System.out.println("请填写出租起始的时期:");
                System.out.print("出租起始时间(年-月-日):");
                String rentDate = sc.next();

                System.out.println("请填写出租时长:");
                System.out.print("出租时长(天):");
                int rentDay = sc.nextInt();
                ((Van)vehicle).getRentRecord().add(vehicle.getType() + "被" + customerID + "借走，从" + rentDate + "开始，借出" + rentDay + "天");
                vehicle.setRented(true);

                return true;
            }
        }
        return false;
    }

    private boolean rentVan(Scanner sc, String rentId) {
        for (Vehicle vehicle : vanList) {
            if (vehicle.getId().equals(rentId)) {
                //找到了这辆车
                //判断这辆车是否被出租
                if (vehicle.isRented()) {
                    return false;
                }
                System.out.println("请填写您的顾客编号:");
                System.out.print("顾客编号:");
                int customerID = sc.nextInt();

                System.out.println("请填写出租起始的时期:");
                System.out.print("出租起始时间(年-月-日):");
                String rentDate = sc.next();

                System.out.println("请填写出租时长:");
                System.out.print("出租时长(天):");
                int rentDay = sc.nextInt();
                ((Van)vehicle).getRentRecord().add(vehicle.getType() + "被" + customerID + "借走，从" + rentDate + "开始，借出" + rentDay + "天");
                vehicle.setRented(true);

                return true;
            }
        }
        return false;
    }

    public void load() {
        File vanFile = new File(VANPATH);
        File carFile = new File(CARPATH);

        if (!vanFile.exists()) {
            //判断父级目录是否存在
            if (!vanFile.getParentFile().exists()) {
                vanFile.getParentFile().mkdirs();//进行级联创建
                try {
                    vanFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    vanFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!carFile.exists()) {
            if (!carFile.getParentFile().exists()) {
                carFile.getParentFile().mkdirs();
                try {
                    carFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    carFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try (FileInputStream fileInputStreamVan = new FileInputStream(vanFile);
             FileInputStream fileInputStreamCar = new FileInputStream(carFile)) {
            byte[] bytesVan = fileInputStreamVan.readAllBytes();
            byte[] bytesCar = fileInputStreamCar.readAllBytes();
            String jsonVan = new String(bytesVan);
            String jsonCar = new String(bytesCar);

            List<Vehicle> carList = JSON.parseArray(jsonCar, Vehicle.class);
            List<Vehicle> vanList = JSON.parseArray(jsonVan, Vehicle.class);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
