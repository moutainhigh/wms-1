import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lilei on 2017/7/15.

 */
public class JdbcTest {

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn;

            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=ReportServer";
            String username = "sa";
            String password = "123";

            conn = DriverManager.getConnection(url,username,password);

            if (conn != null) {
                System.out.print("success!");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
