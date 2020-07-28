package com.banyuan.jdbc;

import java.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JdbcUtil {

    private static final String user = "root";
    private static final String pwd = "12348765";

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/MarketBillSystem?useSSL=false";

    protected static Connection connection;
    protected static PreparedStatement preparedStatement;
    protected static ResultSet resultSet;

    // 初始化
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(DB_URL, user, pwd);
        } catch (SQLException throwables) {
            throw new SQLException("建立连接失败!");
        }
        return connection;
    }

    // 查询单条数据结果
    public static Map<String, Object> queryOne(String sql, Object... params) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colLen = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 0; i < colLen; i++) {
                    String colName = metaData.getColumnName(i + 1);
                    Object colValue = resultSet.getObject(colName);
                    if (colValue == null) {
                        colValue = "";
                    }
                    map.put(colName, colValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // TODO 关闭连接
            close(connection, preparedStatement, resultSet);
        }
        return map;
    }

    // 查询多条结果
    public static List<Map<String, Object>> queryAll(String sql, Object... params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colLen = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < colLen; i++) {
                    String colName = metaData.getColumnName(i + 1);
                    Object colValue = resultSet.getObject(colName);
                    if (colValue == null) {
                        colValue = "";
                    }
                    map.put(colName, colValue);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // TODO 关闭连接
            close(connection, preparedStatement, resultSet);
        }
        return list;
    }

    // 插入 更新 删除数据
    public static int update(String sql, Object... params) throws SQLException {
        int result = 0;
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // TODO 关闭连接
            close(connection, preparedStatement, resultSet);
        }
        return result;
    }

    // 关闭连接
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
