package club.banyuan.dao.impl;

import club.banyuan.dao.ProductDao;
import club.banyuan.pojo.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
    public ProductDaoImpl(Connection conn){
        super(conn);
    }

    @Override
    public Product tableToClass(ResultSet rs) throws Exception {
        Product product = new Product();
        product.setId(rs.getInt(1));
        product.setName(rs.getString(2));
        product.setDescription(rs.getString(3));
        product.setPrice(rs.getDouble(4));
        product.setStock(rs.getInt(5));
        product.setCategoryLevel1Id(rs.getInt(6));
        product.setCategoryLevel2Id(rs.getInt(7));
        product.setCategoryLevel3Id(rs.getInt(8));
        product.setFileName(rs.getString(9));
        product.setIsDelete(rs.getInt(10));

        return product;
    }

    @Override
    public List<Product> getProductByKeyWords(String keyWords) throws Exception {
        String sql = "select * from product where name like ? or description like ?";
        Object[] param = new Object[2];
        param[0] = "%"+keyWords+"%";
        param[1] = "%"+keyWords+"%";
        ResultSet rs = executeQuery(sql,param);
        List<Product> productList = new ArrayList<>();
        while (rs.next()){
            Product product = tableToClass(rs);
            productList.add(product);
        }
        closeResource(rs);
        return productList;
    }

    @Override
    public Product getProductById(int id) throws Exception {
        Product product = null;
        String sql = "select * from product where id=?";
        Object[] param = new Object[]{id};
        ResultSet rs = this.executeQuery(sql,param);
        while(rs.next()){
            product = tableToClass(rs);
        }
        closeResource(rs);
        return product;
    }
}
