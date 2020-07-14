package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class UdpServer extends Thread{
    private static Scanner sc = new Scanner(System.in);
    private DatagramSocket datagramSocket;

    public UdpServer(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {
        try {
            byte[] bytes = new byte[65536];
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                datagramSocket.receive(datagramPacket);
                System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("服务器停止!");
    }

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5000);
        UdpServer udpServer = new UdpServer(socket);
        System.out.println("连接完成，等待信息传输----------------");
        udpServer.start();
        String keepGoing = "";
        while (!keepGoing.equalsIgnoreCase("quit")) {
            keepGoing = sc.nextLine();
        }
        socket.close();
    }
}
