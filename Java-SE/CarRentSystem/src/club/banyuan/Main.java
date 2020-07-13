package club.banyuan;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static CarRentSystem system = new CarRentSystem();
    private static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        system.load();
        boolean keepGoing = true;
        while (keepGoing) {
            //菜单
            System.out.println("**** 租车系统菜单 ****");
            System.out.println("1.添加汽车");
            System.out.println("2.出租汽车");
            System.out.println("3.归还汽车");
            System.out.println("4.汽车保养");
            System.out.println("5.结束保养");
            System.out.println("6.查询信息");
            System.out.println("7.退出系统");

            System.out.println("请选择您要进行的操作:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // 添加汽车
                    add();
                    break;
                case 2:
                    // 出租
                    rent();
                    break;
                case 3:
                    // 归还
                    returnVehicle();
                    break;
                case 4:
                    // 保养
                    break;
                case 5:
                    // 结束保养
                    break;
                case 6:
                    // 查询信息
                    listPrint();
                    break;
                case 7:
                    // 退出
                    keepGoing = false;
                    break;
            }

        }
//        System.out.println(system.getCarList());
//        System.out.println(system.getVanList());
//        system.rentVehicle();
    }

    private static void listPrint() {
        System.out.println("****************卡车*****************");
        System.out.println(system.getVanList());
        System.out.println("****************轿车*****************");
        System.out.println(system.getCarList());
    }

    private static void returnVehicle() {
        try {
            system.returnVehicle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void rent() {
        try {
            system.rentVehicle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void add() {
        try {
            system.addVehicle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
