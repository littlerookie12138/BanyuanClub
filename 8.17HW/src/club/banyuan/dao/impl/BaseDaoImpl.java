package club.banyuan.dao.impl;

import club.banyuan.dao.IBaseDao;

import java.sql.*;

public abstract class BaseDaoImpl implements IBaseDao {
    protected Connection connection;

    protected PreparedStatement pstm;

    public BaseDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet executeQuery(String sql, Object[] params){
        ResultSet rs=null;
        try {
            pstm = connection.prepareStatement(sql);
            if(params!=null){
                for(int i = 0; i < params.length; i++){
                    pstm.setObject(i+1, params[i]);
                }
            }
            rs = pstm.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    //增删改操作
    public int executeUpdate(String sql,Object[] params){
        int updateRows = 0;
        try {
            pstm = connection.prepareStatement(sql);
            for(int i = 0; i < params.length; i++){
                pstm.setObject(i+1, params[i]);
            }
            updateRows = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            updateRows = -1;
        }

        return updateRows;
    }

    @Override
    public int executeInsert(String sql,Object[] params){
        Long id = 0L;
        try {
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for(int i = 0; i < params.length; i++){
                pstm.setObject(i+1, params[i]);
            }
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
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
    //释放资源
    public boolean closeResource(){
        if(pstm != null){
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean closeResource(ResultSet reSet){
        if(reSet != null){
            try {
                reSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    // 需要重写的方法，将结果集转换为对象
    public abstract Object tableToClass(ResultSet rs) throws Exception;

}
