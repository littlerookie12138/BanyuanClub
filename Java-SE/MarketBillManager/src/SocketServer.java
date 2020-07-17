import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SocketServer extends Thread {
    private static List<User> userList = new ArrayList();
    private Socket socket;
    private static final String PATH = PropUtil.getPath();
    private static MbmRequest mbmRequest;

    public static void load() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else if (!file.getParentFile().isDirectory()) {
                throw new FileNotFoundException("文件夹不存在");
            } else {
                file.createNewFile();
            }
        }


        try (InputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = inputStream.readAllBytes();
            String jsonString = new String(bytes);
            userList = JSONObject.parseArray(jsonString, User.class);
            if (userList == null) {
                userList = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void store() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else if (!file.getParentFile().isDirectory()) {
                throw new FileNotFoundException("文件夹不存在");
            } else {
                file.createNewFile();
            }
        }
        try {
            OutputStream outputStream = new FileOutputStream(file);
            String jsonString = JSONObject.toJSONString(userList);
            outputStream.write(jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            load();
            InputStream clientInput = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientInput));
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // 将浏览器发过来的请求保存在mbmRequest
            mbmRequest = MbmRequest.parse(bufferedReader);
            if (mbmRequest == null) {
                return;
            }
            Map<String, String> receiveMsg = mbmRequest.getFormData();

            String path = mbmRequest.getPath();
            // 从这里搞到此次请求的路径
            // 首先判断是不是根路径
            if ("/".equals(path)) {
                responseHtml("/login.html", dataOutputStream);
            } else if (path.contains(".html")) {
                responseHtml(path, dataOutputStream, checkUser(receiveMsg));
            } else if (path.contains("/css") || path.contains("/images") || path.contains("/js")) {
                responseFile(dataOutputStream, path);
            } else {
                switch (path) {
                    case "/server/login":
                        if (checkUser(receiveMsg)) {
                            responseRedirect("/bill_list.html", dataOutputStream);
                        } else {
                            responseRedirect("/login.html", dataOutputStream);
                        }
                        break;
                    case "/server/user/list":
                        responseUser(userList);
                        break;
                    case "/server/user/modify":
                        if (addUser() == null) {
                            responseRedirect("/modify_failed.html", dataOutputStream);
                        } else {
                            responseRedirect("/user_list.html", dataOutputStream);
                        }
                        break;
                    case "/server/user/delete":
                        deleteUser();
                        responseRedirect("/user_list.html", dataOutputStream);
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

    private void deleteUser() {
        try {
            String payload = mbmRequest.getPayload();
            String[] split = payload.split(":");
            split[1] = split[1].replace("}", "");
            split[1] = split[1].replace("\"", "");

            userList.removeIf(user -> user.getId() == Integer.parseInt(split[1]));

            store();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public User addUser() {
        try {
            Map<String, String> formData = mbmRequest.getFormData();
            boolean nameIsUsed = false;
            int maxId = 0;
            for (User user : userList) {
                if (maxId < user.getId()) {
                    maxId = user.getId();
                }
                if (user.getUserName().equals(formData.get("userName"))) {
                    nameIsUsed = true;
                }
            }
            if (!formData.get("pwd").equals(formData.get("pwdConfirm")) || nameIsUsed) {
                // 用户名密码输入两次错误 或者 用户名被使用
                return null;
            }

            User user = new User();
            user.setId(maxId + 1);
            user.setUserName(formData.get("userName"));
            user.setPwd(formData.get("pwd"));
            if (formData.get("userType").equals("1")) {
                user.setUserTypeStr("经理");
            } else {
                user.setUserTypeStr("普通员工");
            }
            user.setPwdConfirm(formData.get("pwdConfirm"));

            synchronized (userList) {
                userList.add(user);
            }
            store();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void responseUser(Object object) throws IOException {
        String jsonString = JSONObject.toJSONString(object);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeBytes("HTTP/1.1 200 OK");
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.writeBytes("Content-Length: " + jsonString.getBytes().length);
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.writeBytes("Content-Type: application/json;charset=utf-8;");
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.write(jsonString.getBytes());
    }

    private void responseRedirect(String path, DataOutputStream dataOutputStream) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 302 Found".getBytes());
        outputStream.write("\r\n".getBytes());
        // 告知浏览器请求结束，需要再次向请求给定的路径发起请求
        outputStream.write(("Location: " + "http://" + mbmRequest.getHost().replace(" ", "") + path).getBytes());
        outputStream.write("\r\n".getBytes());
    }


    private boolean checkUser(Map<String, String> map) {
        for (User user : userList) {
            if (user.getUserName().equals(map.get("userName")) && user.getPwd().equals(map.get("passWord"))) {
                    return true;
            }
        }
        return false;
    }

    private void responseFile(DataOutputStream dataOutputStream, String path) {
        if (path.contains("/server")) {
            path = path.replace("/server", "");
        }
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

    private void responseHtml(String path, DataOutputStream dataOutputStream, boolean result) throws IOException {
        InputStream resourceAsStream = null;
        try {
            //当我需要返回一个html文件时，找到这个路径
            resourceAsStream = SocketServer.class.getClassLoader().getResourceAsStream("pages" + path);

            if (resourceAsStream == null) {
                // 路径不存在时返回404.html
                resourceAsStream = SocketServer.class.getClassLoader().getResourceAsStream("pages/404.html");
            }

            if (!result && path.contains("login")) {
                // 返回login页面并提示用户名或密码错误
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
                String line = bufferedReader.readLine();
                StringBuilder stringBuilder = new StringBuilder();
                while (line != null && line.length() != 0) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                    if (line.contains("<div class=\"buttons\">")) {
                        stringBuilder.append("<span style=\"color:red; text-align: center; border: 1px solid red;border-radius: 15px;animation:myfirst 3s none;\">用户名或密码错误</span>");
                    }
                    line = bufferedReader.readLine();
                }

                String contentLength = "Content-Length:" + stringBuilder.toString().getBytes().length;
                dataOutputStream.writeBytes("HTTP/1.1 302 OK\r\n");
                dataOutputStream.writeBytes("Content-Type: text/html; charset=utf-8\r\n");
                dataOutputStream.writeBytes(contentLength);
                dataOutputStream.writeBytes("\r\n");
                dataOutputStream.writeBytes("\r\n");
                dataOutputStream.write(stringBuilder.toString().getBytes());
                return;
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
        load();
        ServerSocket serverSocket = new ServerSocket(5000);
        while (true) {
            Socket accept = serverSocket.accept();
            SocketServer socketServer = new SocketServer(accept);
            socketServer.start();
        }


    }
}
