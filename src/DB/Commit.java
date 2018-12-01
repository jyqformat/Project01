package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class  Commit {
    public static void Commit(){
        Connection connection = null;
        try{
            connection.setAutoCommit(false);
            Statement statement =connection.createStatement();
            statement.executeUpdate("insert into user(name) value ('zs')");
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
                System.out.println("发生异常，已经回滚！");
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }
    }
}
