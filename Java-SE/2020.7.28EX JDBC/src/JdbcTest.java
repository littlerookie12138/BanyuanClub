import java.sql.*;

public class JdbcTest {
    private static final String user = "root";
    private static final String pwd = "12348765";

    // JDBC连接驱动
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    // JDBC连接串 MarketBillSystem表示数据库名称
    static final String DB_URL = "jdbc:mysql://localhost:3306/MarketBillSystem?useSSL=false&allowPublicKeyRetrieval=true";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("连接数据库....");

            connection = DriverManager.getConnection(DB_URL, user, pwd);
            System.out.println("实例化 Statement  对象....");
            statement = connection.createStatement();


            // sql语句
            String sql = "select sno \n" +
                    "from score\n" +
                    "group by sno\n" +
                    "having min(degree) > 80 and max(degree) < 90;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int sno = resultSet.getInt("sno");
//                String sname = resultSet.getString("sname");
                System.out.println("学生序号为: " + sno );
            }

            // 先关闭哪个的从创建的顺序反向来
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
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
}
