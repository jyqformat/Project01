package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String url = "jdbc:mysql:" + "//localhost:3306/user";
    private static String root = "root";
    private static String pwd = "jyq6311026";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, root, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}

