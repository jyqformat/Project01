package DB;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaeseTest {
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/user?serverTimezone=UTC&characterEncoding=utf-8";
    public static final String DBUSER = "root";
    public static final String DBPASSWORD = "jyq6311026";


    public static void main(String [] arge) throws Exception{
    Class.forName(DBDRIVER);
        Connection collection = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
        System.out.println(collection);
        ((Connection) collection).close();

    }
}