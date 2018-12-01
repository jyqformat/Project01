package DB;

import java.sql.Connection;
import java.sql.*;
import java.util.List;

public class Batch {
    public static void Batch() {
        Connection connection = null;
        try {
            connection = DBUtil.getCon();
            Statement statement = connection.createStatement();
            statement.addBatch("insert into user (name,gender) value ('张三','男')");
            statement.addBatch("insert into user (name,gender) value ('李四','男')");
            statement.addBatch("delete from user where id =1");
            statement.addBatch("update user set name ='王五' where id =1");
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
