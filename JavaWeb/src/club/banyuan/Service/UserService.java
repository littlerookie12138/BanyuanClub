package club.banyuan.Service;

import club.banyuan.pojo.User;

import java.sql.SQLException;

public interface UserService {
    public User register(User user) throws SQLException;
}
