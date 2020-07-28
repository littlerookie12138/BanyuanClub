package database.jdbc;

import database.systemRefine.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserService {
    // 查询数据库所有User数据
    public static List<Map<String, Object>> getAllUsers() throws SQLException {
        String sql = "select id, pwd, pwdConfirm, userName, userTypeStr, userType from User";

        return JDBCUtil.getDataByList(sql);

    }

    // 查询数据库中某个user数据
    public static Map<String, Object> getUserById(User user) {
        String sql = "select id, pwd, pwdConfirm, userName, userTypeStr, userType from User where id = ?";

        return JDBCUtil.getUserById(sql, user.getId());
    }

    // 向数据库中添加一个user数据
    public static void addUser(User user) {
        String sql = "insert into User (pwd, pwdConfirm, userName, userTypeStr, UserType) values (?, ?, ?, ?, ?)";

        JDBCUtil.update(sql, user.getPwd(), user.getPwdConfirm(), user.getUserName(), user.getUserTypeStr(), user.getUserType());
    }

    // 修改数据库中的某个user数据
    public static void updateUser(User user) {
        String sql = "update User set pwd = ?, pwdConfirm = ?, userName = ?, userTypeStr = ?, userType = ? where id = ?";

        JDBCUtil.update(sql, user.getPwd(), user.getPwdConfirm(), user.getUserName(), user.getUserTypeStr(), user.getUserType(), user.getId());
    }

    // 删除数据库中的某个user数据
    public static void deleteUser(User user) {
        String sql = "delete from User where id = ?";

        JDBCUtil.update(sql, user.getId());
    }

    // 模糊查询user数据
    public static List<Map<String, Object>> fuzzyQuery(User user) throws SQLException {
        String sql = "select id, pwd, pwdConfirm, userName, userTypeStr, userType from User where userName like ?";

        return JDBCUtil.getDataByList(sql, "%" + user.getUserName() + "%");
    }

}
