import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class JDBCTest {
    private static String USER = "root";
    private static String PWD = "12348765";

    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/shoppingstreet?useSSL=false";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }

    public static List<Map<String, Object>> getAllUsers(String sql, Object...patten) {
        List<Map<String, Object>> maplist = new ArrayList<>();
        connection = getConnection();

        try {
            preparedStatement = connection.prepareStatement(sql);
            if (patten != null && patten.length > 0) {
                for (int i = 0; i < patten.length; i++) {
                    preparedStatement.setObject(i + 1, patten[i]);
                }
            }

            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> temp = new HashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object value = resultSet.getObject(columnName);
                    if (value == null) {
                        value = "";
                    }
                    temp.put(columnName, value);
                }

                maplist.add(temp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }

        return maplist;
    }

    public static Map<String, Object> getUserById(String sql, Object...patten) {
        Map<String, Object> objectMap = new HashMap<>();
        connection = getConnection();

        try {
            preparedStatement = connection.prepareStatement(sql);
            if (patten != null && patten.length > 0) {
                for (int i = 0; i < patten.length; i++) {
                    preparedStatement.setObject(i + 1, patten[i]);
                }
            }

            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object value = resultSet.getObject(columnName);
                    if (value == null) {
                        value = "";
                    }
                    objectMap.put(columnName, value);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return objectMap;
    }

    public static int Update(String sql, Object...patten) {
        connection = getConnection();
        int result = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            if (patten != null && patten.length > 0) {
                for (int i = 0; i < patten.length; i++) {
                    preparedStatement.setObject(i + 1, patten[i]);
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

    private static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }



}
