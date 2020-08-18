package club.banyuan.Service.impl;

import club.banyuan.Service.UserService;
import club.banyuan.baseDao.UserDao;
import club.banyuan.baseDao.daoImpl.UserDaoImpl;
import club.banyuan.pojo.User;
import club.banyuan.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public User register(User user) throws SQLException {
        Connection conn = DataSourceUtil.openConnection();
        UserDao userDao = new UserDaoImpl(conn);
        User newUser = userDao.addUser(user);
        DataSourceUtil.closeConnection(conn);
        return newUser;
    }
}
