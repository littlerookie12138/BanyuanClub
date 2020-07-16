import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class HttpServer extends Thread{
    private Socket socket = null;

    public HttpServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 读服务器的请求
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {


            //一行一行读
            MbmRequest mbmRequest = MbmRequest.solveRequest(bufferedReader);
            if (mbmRequest == null) {
                return;
            }

            String path = mbmRequest.getPath();
            if ("/".equals(path)) {
                responseSource(outputStream, "/Login.html", mbmRequest);
            } else if (path.contains("/server/login")) {
                responseHtml(outputStream, "/Welcome.html", mbmRequest);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void responseHtml(OutputStream outputStream, String path, MbmRequest mbmRequest) throws FileNotFoundException {

        InputStream inputStream = null;

        inputStream = HttpServer.class.getClassLoader().getResourceAsStream("pages" + path);
        if (inputStream == null) {
            System.out.println("找不到路径");
            return;
        }

        Map<String, String> map = MbmRequest.getFormData(mbmRequest);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder str = new StringBuilder();
        try {
            String line = bufferedReader.readLine();
            while (line != null && line.length() != 0) {
                if (line.contains("${userName}")) {
                    line = line.replace("${userName}", map.get("userName"));
                }
                if (line.contains("${password}")) {
                    line = line.replace("${password}", map.get("password"));
                }

                str.append(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String contentLength = "Content-Length:" + str.toString().getBytes().length;

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes("HTTP/1.1 200 OK\r\n");
            dataOutputStream.writeBytes("Content-Type: text/html; charset=utf-8\r\n");
            dataOutputStream.writeBytes(contentLength);
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.write(str.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void responseSource(OutputStream outputStream, String path, MbmRequest mbmRequest) throws FileNotFoundException {
        InputStream inputStream = null;


        inputStream = HttpServer.class.getClassLoader().getResourceAsStream("pages" + path);
        if (inputStream == null) {
            System.out.println("找不到路径");
            return;
        }

        try {
            String contentLength = "Content-Length:" + inputStream.available();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes("HTTP/1.1 200 OK\r\n");
            dataOutputStream.writeBytes("Content-Type: text/html; charset=utf-8\r\n");
            dataOutputStream.writeBytes(contentLength);
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.write(inputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);

        while (true) {
            Socket socket = serverSocket.accept();
            HttpServer httpServer = new HttpServer(socket);
            httpServer.start();
        }

    }
}
