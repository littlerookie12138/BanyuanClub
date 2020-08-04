package database.jdbc;

import database.systemRefine.Provider;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ProviderService {
    // 查询数据库所有provider数据
    public static List<Map<String, Object>> getAllProviders() throws SQLException {
        String sql = "select * from Provider";

        return JDBCUtil.getDataByList(sql);

    }

    // 查询数据库中某个provider数据
    public static Map<String, Object> getProviderById(Provider provider) {
        String sql = "select * from Provider where id = ?";

        return JDBCUtil.getUserById(sql, provider.getId());
    }

    // 向数据库中添加一个provider数据
    public static void addProvider(Provider provider) {
        String sql = "insert into Provider (contactPerson, `desc`, name, phone) values (?, ?, ?, ?)";

        JDBCUtil.update(sql, provider.getContactPerson(), provider.getDesc(), provider.getName(), provider.getPhone());
    }

    // 修改数据库中的某个provider数据
    public static void updateProvider(Provider provider) {
        String sql = "update Provider set contactPerson = ?, `desc` = ?, name = ?, phone = ? where id = ?";

        JDBCUtil.update(sql, provider.getContactPerson(), provider.getDesc(), provider.getName(), provider.getPhone(), provider.getId());
    }

    // 删除数据库中的某个user数据
    public static void deleteProvider(Provider provider) {
        String sql = "delete from Provider where id = ?";

        JDBCUtil.update(sql, provider.getId());
    }

    // 模糊查询provider数据
    public static List<Map<String, Object>> fuzzyQuery(Provider provider) throws SQLException {
        String sql = "select * from Provider where `desc` like ? and name like ?";

        return JDBCUtil.getDataByList(sql, "%" + provider.getDesc() + "%", "%" + provider.getName() + "%");
    }
}
