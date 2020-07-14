import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SocketServer extends Thread{
    private List<User> userList = new ArrayList();
    private Socket socket;

    public SocketServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream clientInput = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientInput));
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // 将浏览器发过来的请求保存在mbmRequest
            MbmRequest mbmRequest = MbmRequest.parse(bufferedReader);
            if (mbmRequest == null) {
                return;
            }

            String path = mbmRequest.getPath();
            // 从这里搞到此次请求的路径
            // 首先判断是不是根路径
            if ("/".equals(path)) {
                responseHtml("/login.html", dataOutputStream);
            } else if (path.contains(".html")) {
                responseHtml(path, dataOutputStream);
            } else if (path.contains("/css") || path.contains("/images") || path.contains("/js")) {
                responseFile(dataOutputStream, path);
            } else {
                switch (path) {
//                    case "/server/login":
//                        Map<String, String> receiveMsg = mbmRequest.getFormData();
//                        System.out.println(receiveMsg);
//                        checkUser(receiveMsg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkUser(Map<String, String> map) {

    }

    private void responseFile(DataOutputStream dataOutputStream, String path) {
        InputStream resourceAsStream = SocketServer.class.getClassLoader().getResourceAsStream("pages" + path);

        if (resourceAsStream == null) {
            System.err.println("资源不存在!");
            return;
        }

        try {
            String contentLength = "Content-Length:" + resourceAsStream.available();
            dataOutputStream.writeBytes("HTTP/1.1 200 OK\r\n");
            dataOutputStream.writeBytes(contentLength);
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.write(resourceAsStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseHtml(String path, DataOutputStream dataOutputStream) throws IOException {
        InputStream resourceAsStream = null;
        try {
            //当我需要返回一个html文件时，找到这个路径
            resourceAsStream = SocketServer.class.getClassLoader().getResourceAsStream("pages" + path);

            if (resourceAsStream == null) {
                // 路径不存在时返回404.html
                resourceAsStream = SocketServer.class.getClassLoader().getResourceAsStream("pages/404.html");
            }

            String contentLength = "Content-Length:" + resourceAsStream.available();
            dataOutputStream.writeBytes("HTTP/1.1 200 OK\r\n");
            dataOutputStream.writeBytes("Content-Type: text/html; charset=utf-8\r\n");
            dataOutputStream.writeBytes(contentLength);
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.write(resourceAsStream.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        while (true) {
            Socket accept = serverSocket.accept();
            SocketServer socketServer = new SocketServer(accept);
            socketServer.start();
        }

    }
}
