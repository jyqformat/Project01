package Class;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    static String url = "jdbc:mysql://localhost:3306";
    static String root = "root";
    static String pwd = "jyq6311026";
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getCon(){
        Connection connection = null;
        try{
         connection = DriverManager.getConnection(url,root,pwd);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
