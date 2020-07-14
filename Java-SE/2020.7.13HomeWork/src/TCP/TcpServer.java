package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static final int FIRST_UPPER = 65;
    public static final int FIRST_LOWER = 97;
    public static final int NUM_CHARS = 26;
    public static final int OFFSET = 3;

    private static final String FilePath = "/Users/edz/BanyuanClub/Java-SE/2020.7.13HomeWork/SourceFile";

    // 解密文件的路径
    private static final String decryptPath = "/Users/edz/BanyuanClub/Java-SE/2020.7.13HomeWork/DecrytPath/DecrytFile";

    //加密文件的路径
    private static final String EncryptPath = "/Users/edz/BanyuanClub/Java-SE/2020.7.13HomeWork/EncryptPath/EncryptFile";


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Socket socket = serverSocket.accept();
        System.out.println(socket.getInetAddress().getHostAddress() + " connected-------------------");

        try {
            while (true) {
                // 接受第一次消息，判断加密还是解密
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String way = bufferedReader.readLine();

                if ("加密".equals(way)) {
                    decryptFile(bufferedReader);

                } else if ("解密".equals(way)) {
                    encryptFile(bufferedReader);

                }
            }
        } catch (IOException e) {
            System.out.println("程序结束!");
        }
    }

    private static void encryptFile(BufferedReader bufferedReader) throws IOException {
        System.out.println("解密");
        System.out.println("行数:" + bufferedReader.readLine());
        System.out.println("处理文件-------------");
        //读完后写入文件
        File encryptFile = new File(decryptPath);
        if (!encryptFile.exists()) {
            if (!encryptFile.getParentFile().exists()) {
                new File(encryptFile.getParentFile().getAbsolutePath()).mkdirs();
                encryptFile.createNewFile();
            }
        } else {
            encryptFile.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(encryptFile)));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            for (char c : line.toCharArray()) {
                bufferedWriter.write(caesarDecode(c));
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    private static void decryptFile(BufferedReader bufferedReader) throws IOException {
        System.out.println("加密");
        System.out.println("行数:" + bufferedReader.readLine());
        System.out.println("处理文件-------------");
        //读完后写入文件
        File encryptFile = new File(EncryptPath);
        if (!encryptFile.exists()) {
            if (!encryptFile.getParentFile().exists()) {
                new File(encryptFile.getParentFile().getAbsolutePath()).mkdirs();
                encryptFile.createNewFile();
            }
        } else {
            encryptFile.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(encryptFile)));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            for (char c : line.toCharArray()) {
                bufferedWriter.write(caesarEncode(c));
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    // 解密
    public static char caesarDecode(char ch) {
        if (Character.isUpperCase(ch)) {
            return (char) ((ch - TcpServer.FIRST_UPPER + TcpServer.NUM_CHARS - TcpServer.OFFSET) % TcpServer.NUM_CHARS
                    + TcpServer.FIRST_UPPER);
        } else if (Character.isLowerCase(ch)) {
            return (char) ((ch - TcpServer.FIRST_LOWER + TcpServer.NUM_CHARS - TcpServer.OFFSET) % TcpServer.NUM_CHARS
                    + TcpServer.FIRST_LOWER);
        } else {
            return ch;
        }
    }

    // 加密
    public static char caesarEncode(char ch) {

        if (Character.isUpperCase(ch)) {
            return (char) ((ch - FIRST_UPPER + OFFSET) % NUM_CHARS + FIRST_UPPER);
        } else if (Character.isLowerCase(ch)) {
            return (char) ((ch - FIRST_LOWER + OFFSET) % NUM_CHARS + FIRST_LOWER);
        } else {
            return ch;
        }
    }
}
