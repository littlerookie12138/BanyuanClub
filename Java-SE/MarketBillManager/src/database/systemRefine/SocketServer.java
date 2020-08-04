package database.systemRefine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import database.jdbc.BillService;
import database.jdbc.JDBCUtil;
import database.jdbc.ProviderService;
import database.jdbc.UserService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SocketServer extends Thread {
    private static List<User> userList = new ArrayList();
    private static List<Provider> providerList = new ArrayList();
    private static List<Bill> billList = new ArrayList();

    private Socket socket;
    private static MbmRequest mbmRequest;

    static {
        JDBCUtil jdbcUtil = new JDBCUtil();
    }

    // 加锁对象
    private static char[] arg;

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
                if (path.contains("logout")) {
                    // 退出程序
                    responseHtml("/login.html", dataOutputStream);
                } else if (path.contains("login")) {
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
                                    synchronized (arg) {
                                        responseJson(User.search(new User(mbmRequest.getFormData().get("name"))));
                                    }
                                } else {
                                    responseRedirect("/server/user/list", dataOutputStream);
                                }
                                break;
                            }
                        }
                        synchronized (arg){
                            List<User> temp = new ArrayList<>();
                            for (Map<String, Object> user : UserService.getAllUsers()) {
                                temp.add(JSON.parseObject(JSON.toJSONString(user), User.class));
                            }
                            responseJson(temp);
                        }
                        break;
                    case "/server/user/modify":
                        if (add() == null) {
                            responseRedirect("/modify_failed.html", dataOutputStream);
                        } else {
                            responseRedirect("/user_list.html", dataOutputStream);
                        }
                        break;
                    case "/server/user/delete":
                        delete();
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
                                    responseRedirect("/server/provider/list", dataOutputStream);
                                }
                                break;
                            }
                        }
                        List<Provider> tempProvider = new ArrayList<>();
                        for (Map<String, Object> provider : ProviderService.getAllProviders()) {
                            tempProvider.add(JSON.parseObject(JSON.toJSONString(provider), Provider.class));
                        }
                        responseJson(tempProvider);
                    }
                    break;
                    case "/server/provider/modify": {
                        if (add() == null) {
                            responseRedirect("/providerModify_failed.html", dataOutputStream);
                        } else {
                            responseRedirect("/provider_list.html", dataOutputStream);
                        }
                    }
                    break;
                    case "/server/provider/delete": {
                        delete();
                        responseRedirect("/provider_list.html", dataOutputStream);
                    }
                    break;
                    case "/server/provider/get": {
                        String payload = mbmRequest.getPayload();
                        Provider provider = (Provider) find(payload);
                        responseJson(provider);
                    }
                    break;
                    case "/server/bill/list": {
                        List<Bill> tempBill = new ArrayList<>();
                        for (Map<String, Object> bill : BillService.getAllBills()) {
                            tempBill.add(JSON.parseObject(JSON.toJSONString(bill), Bill.class));
                        }

                        responseJson(tempBill);
                    }
                    break;
                    case "/server/bill/modify": {
                        if (add() == null) {
                            responseRedirect("/billModify_failed.html", dataOutputStream);
                        } else {
                            responseRedirect("/bill_list.html", dataOutputStream);
                        }
                    }
                    break;
                    case "/server/bill/get": {
                        Bill findBill = (Bill) find(mbmRequest.getPayload());
                        responseJson(findBill);
                    }
                    break;
                    case "/server/bill/delete": {
                        delete();
                        responseRedirect("/bill_list.html", dataOutputStream);
                    }
                    break;
                    case "/server/bill/search": {

                        Bill tempBill = new Bill(receiveMsg.get("product"), Integer.valueOf(receiveMsg.get("isPay")));
                        List<Bill> search = new ArrayList<>();
                        for (Map<String, Object> map : BillService.fuzzyQuery(tempBill)) {
                            search.add(JSON.parseObject(JSON.toJSONString(map), Bill.class));
                        }
                        if (search == null) {
                            responseRedirect("server/bill/list", dataOutputStream);
                        } else {
                            responseJson(search);
                        }
                    }
                    break;
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete() {
        String payload = mbmRequest.getPayload();
        String[] split = payload.split(":");
        split[1] = split[1].replace("}", "");
        split[1] = split[1].replace("\"", "");

        if (mbmRequest.getPath().contains("provider")) {
            try {
                Provider provider = new Provider();
                provider.setId(Integer.parseInt(split[1]));

                synchronized (arg) {
                    ProviderService.deleteProvider(provider);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return;
        }

        if (mbmRequest.getPath().contains("bill")) {
            try {
                Bill bill = new Bill();
                bill.setId(Integer.parseInt(split[1]));
                synchronized (arg) {
                    BillService.deleteBill(bill);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return;
        }

        if (mbmRequest.getPath().contains("user")) {
            try {
                User user = new User();
                user.setId(Integer.parseInt(split[1]));

                synchronized (arg) {
                    UserService.deleteUser(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Object find(String payload) {
        payload = MbmRequest.processStr(payload);
        String[] split = payload.split(":|=");

        if (mbmRequest.getPath().contains("provider")) {
            Provider provider = new Provider();
            provider.setId(Integer.parseInt(split[1]));

            synchronized (arg) {
                return JSON.parseObject(JSON.toJSONString(ProviderService.getProviderById(provider)), Provider.class);
            }
        }

        if (mbmRequest.getPath().contains("bill")) {
            Bill bill = new Bill();
            bill.setId(Integer.parseInt(split[1]));

            synchronized (arg) {
                return JSON.parseObject(JSON.toJSONString(BillService.getBillById(bill)), Bill.class);
            }
        }

        if (mbmRequest.getPath().contains("user")) {
            User user = new User();
            user.setId(Integer.parseInt(split[1]));

            synchronized (arg) {
                return JSON.parseObject(JSON.toJSONString(UserService.getUserById(user)), User.class);
            }
        }

        return null;
    }


    private Object add() throws IOException, SQLException {
        int maxID = 0;
        Map<String, String> formData = mbmRequest.getFormData();

        // 处理provider
        if (mbmRequest.getPath().contains("provider")) {
            if (formData.get("id").equals("0")) {
                Provider provider = new Provider();
                provider.setName(formData.get("name"));
                provider.setContactPerson(formData.get("contactPerson"));
                provider.setDesc(formData.get("desc"));
                provider.setPhone(formData.get("phone"));

                if (!Provider.check(provider)) {
                    return null;
                }

                synchronized (arg) {
                    ProviderService.addProvider(provider);
                }
                return provider;
            }

            Provider modify = new Provider();
            modify.setId(Integer.parseInt(formData.get("id")));
            modify.setContactPerson(formData.get("contactPerson"));
            modify.setDesc(formData.get("desc"));
            modify.setName(formData.get("name"));
            modify.setPhone(formData.get("phone"));
            if (!Provider.check(modify)) {
                return null;
            }

            synchronized (arg) {
                ProviderService.updateProvider(modify);
            }
            return modify;
        }

        // 处理账单
        if (mbmRequest.getPath().contains("bill")) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if (formData.get("id").equals("0")) {
                Bill bill = new Bill();
                if (formData.get("isPay").equals("1")) {
                    bill.setIsPayStr("已付款");
                } else if (formData.get("isPay").equals("0")) {
                    bill.setIsPayStr("未付款");
                }
                bill.setIsPay(Integer.parseInt(formData.get("isPay")));
                bill.setMoney(formData.get("money"));
                bill.setProduct(formData.get("product"));
                bill.setProviderId(formData.get("providerId"));
                bill.setProviderName(formData.get("providerName"));
                bill.setUpdateTime(simpleDateFormat.format(date));

                if (!Bill.check(bill)) {
                    return null;
                }

                synchronized (arg) {
                    BillService.addBill(bill);
                }
                return bill;
            }

            Bill bill = JSON.parseObject(JSON.toJSONString(formData), Bill.class);
            if (formData.get("isPay").equals("1")) {
                bill.setIsPayStr("已付款");
            } else if (formData.get("isPay").equals("0")) {
                bill.setIsPayStr("未付款");
            }
            bill.setUpdateTime(simpleDateFormat.format(date));

            if (!Bill.check(bill)) {
                return null;
            }

            synchronized (arg) {
                BillService.updateBill(bill);
            }
            return bill;
        }

        // 处理User
        if (mbmRequest.getPath().contains("user")) {

            // 如果传来id等于0，表示此次操作为新增
            if (formData.get("id").equals("0")) {
                User user = new User();
                user.setUserName(formData.get("userName"));
                user.setUserType(formData.get("userType"));
                user.setPwd(formData.get("pwd"));
                if (formData.get("userType").equals("1")) {
                    user.setUserTypeStr("经理");
                } else {
                    user.setUserTypeStr("普通员工");
                }
                user.setPwdConfirm(formData.get("pwdConfirm"));
                if (!User.check(user)) {
                    return null;
                }

                synchronized (arg) {
                    UserService.addUser(user);
                }

                return user;
            }


            User modify = new User();
            modify.setId(Integer.parseInt(formData.get("id")));
            modify.setUserName(formData.get("userName"));
            modify.setPwd(formData.get("pwd"));
            modify.setUserType(formData.get("userType"));
            if (formData.get("userType").equals("1")) {
                modify.setUserTypeStr("经理");
            } else {
                modify.setUserTypeStr("普通员工");
            }
            modify.setPwdConfirm(formData.get("pwdConfirm"));

            if (!User.check(modify)) {
                return null;
            }

            synchronized (arg) {
                UserService.updateUser(modify);
            }
            return modify;
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


    private boolean checkUser(Map<String, String> map) throws SQLException {
        if (map == null) {
            return false;
        }
        if (map.get("userName").equals("") || map.get("passWord").equals("")) {
            return false;
        }
        for (Map<String, Object> allUser : UserService.getAllUsers()) {

            if (allUser.get("userName").equals(map.get("userName")) && allUser.get("pwd").toString().equals(map.get("passWord"))) {
                return true;
            }
        }
        return false;
    }

    public static User findUser(String id) {
        // 传进来的id {id:x} 对其进行处理
        id = MbmRequest.processStr(id);
        String[] split = id.split(":|=");
        User user = new User();
        user.setId(Integer.parseInt(split[1]));

//        List<database.system.User> collect = userList.stream().filter(user -> user.getId() == Integer.parseInt(split[1])).collect(Collectors.toList());
//        Optional<User> first = userList.stream().filter(user -> user.getId() == Integer.parseInt(split[1])).findFirst();
        return JSON.parseObject(JSON.toJSONString(UserService.getUserById(user)), User.class);

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

    public static List<Bill> getBillList() {
        return billList;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5020);
        while (true) {
            Socket accept = serverSocket.accept();
            SocketServer socketServer = new SocketServer(accept);
            socketServer.start();
        }

    }
}
