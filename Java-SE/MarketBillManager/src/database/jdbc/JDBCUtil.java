package database.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {
    private static String USER = "root";
    private static String pwd = "12348765";

    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/MarketBillSystem?useSSL=false";
    //   mysql8.0版本要求加上时区                                                               &serverTimezone=UTC

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    // 加载数据库驱动
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取数据库连接
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }


    // 查询所有数据库数据
    public static List<Map<String, Object>> getDataByList(String sql, Object...pattern) throws SQLException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        connection = getConnection();

        try {
            preparedStatement = connection.prepareStatement(sql);
            if (pattern != null && pattern.length > 0) {
                for (int i = 0; i < pattern.length; i++) {
                    preparedStatement.setObject(i + 1, pattern[i]);
                }
            }

            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int column = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> simple = new HashMap<>();
                for (int i = 0; i < column; i++) {
                    String columnName = resultSetMetaData.getColumnName(i + 1);
                    Object value = resultSet.getObject(columnName);
                    if (value == null) {
                        value = "";
                    }
                    simple.put(columnName, value);
                }
                mapList.add(simple);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return mapList;
    }

    public static Map<String, Object> getUserById(String sql, Object...pattern) {
        Map<String, Object> map = new HashMap<>();
        connection = getConnection();

        try {
            preparedStatement = connection.prepareStatement(sql);
            if (pattern != null && pattern.length != 0) {
                for (int i = 0; i < pattern.length; i++) {
                    preparedStatement.setObject(i + 1, pattern[i]);
                }
            }

            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colLen = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 0; i < colLen; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object value = resultSet.getObject(columnName);
                    if (value == null) {
                        value = "";
                    }

                    map.put(columnName, value);
                }
            }

            return map;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    public static int update(String sql, Object...pattern) {
        connection = getConnection();
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (pattern != null && pattern.length != 0) {
                for (int i = 0; i < pattern.length; i++) {
                    preparedStatement.setObject(i + 1, pattern[i]);
                }
            }
            result = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return result;
    }

    // 关闭所有连接
    private static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
