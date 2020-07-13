package TCP;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    private static Scanner sc = new Scanner(System.in);

    // 解密文件的路径
    private static final String decryptPath = "/Users/edz/BanyuanClub/Java-SE/2020.7.13HomeWork/DecrytPath";

    //加密文件的路径
    private static final String EncryptPath = "/Users/edz/BanyuanClub/Java-SE/2020.7.13HomeWork/EncryptPath";

    //源文件的路径
    private static final String FilePath = "/Users/edz/BanyuanClub/Java-SE/2020.7.13HomeWork/SourceFile";

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 5000);
        System.out.println("连接已建立------------------");
        OutputStream outputStream = socket.getOutputStream();

        System.out.println("请选择是否加密:(1代表加密，2代表不加密)");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                // 加密 传输一段byte过去
                encryptedTransmission(outputStream);
                break;
            case 2:
                // 解密
                decryptTransmission(outputStream);
                break;
            default:
                break;
        }
        Thread.sleep(10000);
        socket.close();
    }

    private static void decryptTransmission(OutputStream out) throws IOException, InterruptedException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write("解密");
        writer.newLine();
        writer.flush();

        File file = new File(FilePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();

        int count = 0;
        String line = reader.readLine();
        while (line != null) {
            count++;
            builder.append(line);
            builder.append(System.lineSeparator());
            line = reader.readLine();
        }

        writer.write(count + "");
        writer.newLine();
        writer.write(builder.toString());
        writer.flush();
    }

    private static void encryptedTransmission(OutputStream out) throws IOException, InterruptedException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write("加密");
        writer.newLine();
        writer.flush();

        File file = new File(FilePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();

        int count = 0;
        String line = reader.readLine();
        while (line != null) {
            count++;
            builder.append(line);
            builder.append(System.lineSeparator());
            line = reader.readLine();
        }

        writer.write(count + "");
        writer.newLine();
        writer.write(builder.toString());
        writer.flush();
    }
}
