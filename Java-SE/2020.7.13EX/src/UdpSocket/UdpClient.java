package UdpSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    public static void main(String[] args) throws IOException {

        DatagramSocket socket = new DatagramSocket(5050);

        String name = "霸道石少爱上翔";

        byte[] bytes = name.getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.10.255"), 5000);
        socket.send(packet);
        socket.close();
    }

}
