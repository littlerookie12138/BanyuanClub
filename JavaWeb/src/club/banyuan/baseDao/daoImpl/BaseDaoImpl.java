package club.banyuan.baseDao.daoImpl;

import club.banyuan.baseDao.IBaseDao;

import java.sql.*;

public abstract class BaseDaoImpl implements IBaseDao {
    protected Connection connection;
    protected PreparedStatement preparedStatement;

    public BaseDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet executeQuery(String sql, Object[] params) {
        ResultSet rs=null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if(params!=null){
                for(int i = 0; i < params.length; i++){
                    preparedStatement.setObject(i+1, params[i]);
                }
            }
            rs = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    // 增删改操作
    public int executeUpdate(String sql, Object[] params) {
        int updateRows = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i < params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            updateRows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            updateRows = -1;
        }

        return updateRows;
    }

    @Override
    public int executeInsert(String sql, Object[] params) {
        Long id = 0L;
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for(int i = 0; i < params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
                System.out.println("数据主键：" + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
            id =null;
        }

        return id.intValue();
    }

    @Override
    public boolean closeResource() {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean closeResource(ResultSet reSet) {
        if (reSet != null) {
            try {
                reSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public abstract Object tableToClass(ResultSet rs) throws Exception ;
}
