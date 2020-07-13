package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class UdpServer {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5000);
        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        System.out.println("连接完成，等待信息传输----------------");
        String keepGoing = "";
        while (!keepGoing.equalsIgnoreCase("quit")) {
            socket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
            keepGoing = sc.nextLine();
        }
    }
}
