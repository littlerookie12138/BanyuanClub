package club.banyuan.service.impl;

import club.banyuan.dao.ProductDao;
import club.banyuan.dao.impl.ProductDaoImpl;
import club.banyuan.dao.util.DataSourceUtil;
import club.banyuan.pojo.Product;
import club.banyuan.service.ProductService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> getProductByKeyWords(String keyWords) throws Exception {
        Connection conn = DataSourceUtil.openConnection();
        ProductDao productDao = new ProductDaoImpl(conn);
        List<Product> productList = productDao.getProductByKeyWords(keyWords);
        DataSourceUtil.closeConnection(conn);
        return productList;
    }

    @Override
    public Product getProductById(int id) throws Exception {
        Connection conn = DataSourceUtil.openConnection();
        ProductDao productDao = new ProductDaoImpl(conn);
        Product product = productDao.getProductById(id);
        DataSourceUtil.closeConnection(conn);
        return product;
    }
}
