package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        System.out.println("连接完成等待发送数据---------------------");
        boolean keepGoing = true;
        while (keepGoing) {
            String sourceText = sc.nextLine();
            if (sourceText.equalsIgnoreCase("quit")) {
                keepGoing = false;
            }
            String[] str = sourceText.split(" ");
            if (!checkInput(str)) {
                System.out.println("输出不合法!");
                return;
            }
            byte[] msg = str[2].getBytes();
            DatagramPacket pocket = new DatagramPacket(msg, msg.length, InetAddress.getByName(str[0]), Integer.parseInt(str[1]));
            socket.send(pocket);
        }

        socket.close();
    }

    private static boolean checkInput(String[] str) {
        if (str.length != 3) {
            System.out.println("长度不对");
            return false;
        } else if (!checkIP(str[0])) {
            System.out.println("IP不对");
            return false;
        } else if (!checkPort(str[1])) {
            System.out.println("端口不对");
            return false;
        }

        return true;
    }

    private static boolean checkPort(String s) {
        int port = Integer.parseInt(s);
        if (port < 0 || port > 65535) {
            return false;
        }
        return true;
    }

    private static boolean checkIP(String s) {
        String[] ip = s.split("\\.");
        if (ip.length != 4) {
            return false;
        }

        return true;
    }

}
