package club.banyuan.dao;

import java.sql.ResultSet;

public interface IBaseDao {
    public ResultSet executeQuery(String sql, Object[] params);
    public int executeUpdate(String sql,Object[] params);
    public int executeInsert(String sql,Object[] params);
    public boolean closeResource();
    public boolean closeResource(ResultSet reSet);
    public Object tableToClass(ResultSet rs) throws Exception;
}
