import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Predicate;

public class SocketServer extends Thread {
    private static List<User> userList = new ArrayList();
    private static List<Provider> providerList = new ArrayList();
    private Socket socket;
    private static final String USERPATH = PropUtil.getProp("user.store.path");
    private static final String PROVIDERPATH = PropUtil.getProp("provider.store.path");
    private static MbmRequest mbmRequest;

    static {
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() throws IOException {
        File userFile = new File(USERPATH);
        File providerFile = new File(PROVIDERPATH);
        checkFileExist(userFile);
        checkFileExist(providerFile);

        try (FileInputStream userInputStream = new FileInputStream(userFile);
             FileInputStream providerInputStream = new FileInputStream(providerFile);
        ) {

            byte[] bytes = userInputStream.readAllBytes();
            byte[] providerBytes = providerInputStream.readAllBytes();

            String jsonString = new String(bytes);
            String providerJsonString = new String(providerBytes);
            userList = JSONObject.parseArray(jsonString, User.class);
            providerList = JSONObject.parseArray(providerJsonString, Provider.class);

            if (userList == null) {
                userList = new ArrayList<>();
            }

            if (providerList == null) {
                providerList = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void checkFileExist(File userFile) throws IOException {
        if (!userFile.exists()) {
            if (!userFile.getParentFile().exists()) {
                userFile.getParentFile().mkdirs();
                userFile.createNewFile();
            } else if (!userFile.getParentFile().isDirectory()) {
                throw new FileNotFoundException("文件夹不存在");
            } else {
                userFile.createNewFile();
            }
        }
    }

    private static void store() throws IOException {
        File file = new File(USERPATH);
        File providerFile = new File(PROVIDERPATH);
        checkFileExist(file);
        checkFileExist(providerFile);

        try {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStream providerOutputStream = new FileOutputStream(providerFile);
            String jsonString = JSONObject.toJSONString(userList);
            String providerJsonString = JSONObject.toJSONString(providerList);
            outputStream.write(jsonString.getBytes());
            providerOutputStream.write(providerJsonString.getBytes());
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

            // 开启浏览器的输入流以及输出流
            InputStream clientInput = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientInput));
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // 将浏览器发过来的请求保存在mbmRequest
            mbmRequest = MbmRequest.parse(bufferedReader);
            if (mbmRequest == null) {
                return;
            }

            // 保存浏览器发送的数据，用于后面的判断
            Map<String, String> receiveMsg = null;
            if (mbmRequest.getPayload() != null) {
                receiveMsg = mbmRequest.getFormData();
            }

            String path = mbmRequest.getPath();
            // 从这里搞到此次请求的路径
            // 首先判断是不是根路径
            if ("/".equals(path)) {
                responseHtml("/login.html", dataOutputStream);
            } else if (path.contains(".html")) {
                // 如果路径中包含.html，说明请求到html文件
                if (path.contains("login")) {
                    // 请求这个页面说明登录失败
                    responseHtml(path, dataOutputStream, false);
                } else {
                    responseHtml(path, dataOutputStream);
                }
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
                        if (mbmRequest.getPayload() != null) {
                            if (mbmRequest.getFormData().get("flag").equals("search")) {
                                if (!mbmRequest.getFormData().get("name").equals("")) {
                                    responseJson(User.search(new User(mbmRequest.getFormData().get("name"))));
                                } else {
                                    responseJson(userList);
                                }
                                break;
                            }
                        }
                        responseJson(userList);
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
                        break;
                    case "/server/user/get": {
                        String payload = mbmRequest.getPayload();
                        User user = findUser(payload);
                        responseJson(user);
                    }
                    break;
                    case "/server/provider/list": {
                        if (mbmRequest.getPayload() != null) {
                            if (mbmRequest.getFormData().get("flag").equals("search")) {
                                if (!mbmRequest.getFormData().get("name").equals("") || !mbmRequest.getFormData().get("desc").equals("")) {
                                    responseJson(Provider.search(new Provider(mbmRequest.getFormData().get("name"), mbmRequest.getFormData().get("desc"))));
                                } else {
                                    responseJson(providerList);
                                }
                                break;
                            }
                        }
                        responseJson(providerList);
                    }
                    break;
                    case "/server/provider/modify": {
                        if (add() == null) {
                            responseRedirect("/modify_failed.html", dataOutputStream);
                        } else {
                            responseRedirect("/provider_list.html", dataOutputStream);
                        }
                    }
                    break;
                    case "/server/provider/delete": {
                        deleteProvider();
                        responseRedirect("/provider_list.html", dataOutputStream);
                    }
                    break;
                    case "/server/provider/get": {
                        String payload = mbmRequest.getPayload();
                        Provider provider = findProvider(payload);
                        responseJson(provider);
                    }
                    break;
                    case "/server/bill/list": {

                    }
                    break;
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

    private Provider findProvider(String payload) {
        payload = MbmRequest.processStr(payload);
        String[] split = payload.split(":|=");

        Optional<Provider> first = providerList.stream().filter(provider -> provider.getId() == Integer.parseInt(split[1])).findFirst();
        return first.orElse(null);
//        List<User> collect = userList.stream().filter(user -> user.getId() == Integer.parseInt(split[1])).collect(Collectors.toList());
    }

    private void deleteProvider() {
        try {
            String payload = mbmRequest.getPayload();
            String[] split = payload.split(":");
            split[1] = split[1].replace("}", "");
            split[1] = split[1].replace("\"", "");

            synchronized (providerList) {
                providerList.removeIf(user -> user.getId() == Integer.parseInt(split[1]));
            }

            store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Provider add() throws IOException {
        int maxID = 0;
        Map<String, String> formData = mbmRequest.getFormData();

        // 判断本次操作是否为修改操作
        for (Provider provider : providerList) {
            if (provider.getId() == Integer.parseInt(formData.get("id"))) {
                // 说明本次操作是修改
                Optional<Provider> id = providerList.stream().filter(new Predicate<Provider>() {
                    @Override
                    public boolean test(Provider provider) {
                        try {
                            return provider.getId() == Integer.parseInt(mbmRequest.getFormData().get("id"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                }).findFirst();

                if (id.isPresent()) {
                    id.get().setName(formData.get("name"));
                    id.get().setContactPerson(formData.get("contactPerson"));
                    id.get().setDesc(formData.get("desc"));
                    id.get().setPhone(formData.get("phone"));
                }
                store();
                return id.get();
            }
        }

        // 包含供应商信息
        Provider provider = new Provider();
        if (providerList.isEmpty()) {
            provider.setId(1);
            provider.setName(formData.get("name"));
            provider.setContactPerson(formData.get("contactPerson"));
            provider.setDesc(formData.get("desc"));
            provider.setPhone(formData.get("phone"));
            synchronized (providerList) {
                providerList.add(provider);
            }

            store();
            return provider;
        }



        Optional<Provider> max = providerList.stream().max(Comparator.comparing(Provider::getId));
        if (max.isPresent()) {
            maxID = max.get().getId();
        }

        provider.setId(maxID + 1);
        provider.setName(formData.get("name"));
        provider.setContactPerson(formData.get("contactPerson"));
        provider.setDesc(formData.get("desc"));
        provider.setPhone(formData.get("phone"));
        synchronized (providerList) {
            providerList.add(provider);
        }
        store();

        return provider;
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
            boolean isChanged = false;
            int maxId = 0;
            User changedUser = null;

            for (User user : userList) {
                if (maxId < user.getId()) {
                    maxId = user.getId();
                }
                if (user.getUserName().equals(formData.get("userName"))) {
                    nameIsUsed = true;
                }

                if (user.getId() == Integer.parseInt(formData.get("id"))) {
                    changedUser = user;
                    isChanged = true;
                }
            }
            if (!formData.get("pwd").equals(formData.get("pwdConfirm")) || nameIsUsed) {
                // 用户名密码输入两次错误 或者 用户名被使用
                if (!isChanged) {
                    return null;
                }
            }
            if (isChanged) {
                // 证明本次操作是修改
                changedUser.setUserName(formData.get("userName"));
                changedUser.setPwd(formData.get("pwd"));
                changedUser.setUserType(formData.get("userType"));
                if (formData.get("userType").equals("1")) {
                    changedUser.setUserTypeStr("经理");
                } else {
                    changedUser.setUserTypeStr("普通员工");
                }
                changedUser.setPwdConfirm(formData.get("pwdConfirm"));

                store();
                return changedUser;
            }
            User user = new User();
            user.setId(maxId + 1);
            user.setUserName(formData.get("userName"));
            user.setUserType(formData.get("userType"));
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

    private void responseJson(Object object) throws IOException {
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

    public static User findUser(String id) {
        // 传进来的id {id:x} 对其进行处理
        id = MbmRequest.processStr(id);
        String[] split = id.split(":|=");

//        List<User> collect = userList.stream().filter(user -> user.getId() == Integer.parseInt(split[1])).collect(Collectors.toList());
        Optional<User> first = userList.stream().filter(user -> user.getId() == Integer.parseInt(split[1])).findFirst();

        return first.orElse(null);

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
            if (path.contains("?id=")) {
                String[] split = path.split("\\?");
                path = split[0];
            }

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

    public static List<User> getUserList() {
        return userList;
    }

    public static List<Provider> getProviderList() {
        return providerList;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5050);
        while (true) {
            Socket accept = serverSocket.accept();
            SocketServer socketServer = new SocketServer(accept);
            socketServer.start();
        }

    }
}
