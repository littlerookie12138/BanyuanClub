package club.banyuan.dao.impl;

import club.banyuan.dao.UserDao;
import club.banyuan.pojo.User;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    public UserDaoImpl(Connection conn){
        super(conn);
    }

    @Override
    public User getUserByUserNameAndPwd(String username, String password) throws Exception {
        User user = null;
        String sql = "select * from user where loginName=? and password=?";
        ResultSet rs = executeQuery(sql,new Object[]{username,password});
        if (rs.next()) {
            user = tableToClass(rs);
        }
        this.closeResource(rs);
        return user;
    }

    @Override
    public User addUser(User user) {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?)";
        Object[] param = new Object[9];
        param[0]=null;
        param[1]=user.getLoginName();
        param[2]=user.getUserName();
        param[3]=user.getPassword();
        param[4]=user.getSex();
        param[5]=user.getIdentityCode();
        param[6]=user.getEmail();
        param[7]=user.getMobile();
        param[8]=user.getType();
        int id = executeInsert(sql,param);
        user.setId(id);

        closeResource();
        return user;
    }

    @Override
    public User tableToClass(ResultSet rs) throws Exception {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setLoginName(rs.getString(2));
        user.setUserName(rs.getString(3));
        user.setPassword(rs.getString(4));
        user.setSex(rs.getInt(5));
        user.setIdentityCode(rs.getString(6));
        user.setEmail(rs.getString(7));
        user.setMobile(rs.getString(8));
        user.setType(rs.getInt(9));
        return user;
    }
}
