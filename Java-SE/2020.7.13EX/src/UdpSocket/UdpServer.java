package UdpSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(5000);
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        while (true){
            socket.receive(packet);
//            System.out.println(packet.getAddress().getHostAddress());
//            System.out.println(packet.getPort());
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
            System.out.println();
        }


    }
}
