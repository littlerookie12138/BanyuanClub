import com.alibaba.fastjson.JSON;

import java.util.*;

public class UserService {
    // 获取所有用户
    public static List<User> getAllUsers() {
        String sql = "select * from user";

        List<Map<String, Object>> allUsers = JDBCTest.getAllUsers(sql);

        List<User> userList = new ArrayList<>();
        for (Map<String, Object> allUser : allUsers) {
            User user = JSON.parseObject(JSON.toJSONString(allUser), User.class);
            userList.add(user);
        }

        return userList;
    }

    // 添加用户
    public static void addUser(User user) {
        String sql = "insert into user(loginName, userName, password, sex, identityCode, email, mobile, type) values(?, ?, ?, ?, ?, ?, ?, ?)";

        JDBCTest.Update(sql, user.getLoginName(), user.getUserName(), user.getPassword(), user.getSex(), user.getIdentityCode(), user.getEmail(), user.getMobile(), user.getType());
    }

    // 删除用户  找到那个id匹配的用户
    public static void deleteUser(User user) {

    }

    public static void main(String[] args) {
        List<User> allUsers = getAllUsers();
        for (User allUser : allUsers) {
            System.out.println(allUser);
        }

        User user = new User();
        user.setLoginName("asda");
        user.setUserName("asda");
        user.setPassword("asda");
        user.setSex(1);
        user.setIdentityCode("asdafa");
        user.setEmail("asdasd");
        user.setMobile("asdag");
        user.setType(1);

        addUser(user);

        for (User us : getAllUsers()) {
            System.out.println(us);
        }

    }
}
