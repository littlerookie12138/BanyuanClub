package database.jdbc;

import database.systemRefine.Bill;
import database.systemRefine.Provider;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BillService {
    // 查询数据库所有bill数据
    public static List<Map<String, Object>> getAllBills() throws SQLException {
        String sql = "select * from Bill";

        return JDBCUtil.getDataByList(sql);

    }

    // 查询数据库中某个bill数据
    public static Map<String, Object> getBillById(Bill bill) {
        String sql = "select * from Bill where id = ?";

        return JDBCUtil.getUserById(sql, bill.getId());
    }

    // 向数据库中添加一个bill数据
    public static void addBill(Bill bill) {
        Provider providerById = bill.getProviderById();
        String sql = "insert into Bill (isPay, isPayStr, money, product, providerName, updateTime, providerId) values (?, ?, ?, ?, ?, ?, ?)";

        JDBCUtil.update(sql, bill.getIsPay(), bill.getIsPayStr(), bill.getMoney(), bill.getProduct(), providerById.getName(), bill.getUpdateTime(), bill.getProviderId());
    }

    // 修改数据库中的某个bill数据
    public static void updateBill(Bill bill) {
        Provider providerById = bill.getProviderById();
        String sql = "update Bill set isPay = ?, isPayStr = ?, money = ?, product = ?, providerName = ?, updateTime = ?, providerId = ? where id = ?";

        JDBCUtil.update(sql, bill.getIsPay(), bill.getIsPayStr(), bill.getMoney(), bill.getProduct(), providerById.getName(), bill.getUpdateTime(), bill.getProviderId(), bill.getId());
    }

    // 删除数据库中的某个bill数据
    public static void deleteBill(Bill bill) {
        String sql = "delete from Bill where id = ?";

        JDBCUtil.update(sql, bill.getId());
    }

    // 模糊查询bill数据
    public static List<Map<String, Object>> fuzzyQuery(Bill bill) throws SQLException {
        String sql = "select * from Bill where product like ? and isPay = ?";
        if (bill.getIsPay() == -1) {
            String newSql = "select * from Bill where product like ?";
            return JDBCUtil.getDataByList(newSql, "%" + bill.getProduct() + "%");
        }
        return JDBCUtil.getDataByList(sql, "%" + bill.getProduct() + "%", bill.getIsPay());
    }
}
