package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class aBatch {
    public static void aBatch(){
        try{
            Connection connection = DBUtil.getCon();
            String sql = "insert into user (name,gender) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"zhangsha");
            preparedStatement.setString(2,"男");
            preparedStatement.addBatch();
            preparedStatement.setString(1,"wangwu");
            preparedStatement.setString(2,"男");
            preparedStatement.addBatch();
            int [] i = preparedStatement.executeBatch();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
