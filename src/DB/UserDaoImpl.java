package DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public void add(User user) {
        // 获得数据库连接对象
        Connection con = DBUtil.getCon();
        // 定义SQL语句
        String sql = "insert into "
                + " user (loginName,password,name,age,gender)"
                + "  values(?,?,?,?,?)";
        // 获得预编译对象
        //System.out.println(sql);
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            // 对sql语句中的占位符设值
            pst.setString(1, user.getLoginName());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getName());
            pst.setInt(4, user.getAge());
            pst.setString(5, user.getGender());
            // 执行sql语句
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    public void update(int id, User user) {
        // 获得数据库连接对象
        Connection con = DBUtil.getCon();
        // 定义SQL语句
        String sql = "update user set password=?,age=?,gender=?,name=? where id=?";
        // 获得预编译对象
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            // 对sql语句中的占位符设值
            pst.setString(1, user.getPassword());
            pst.setInt(2, user.getAge());
            pst.setString(3, user.getGender());
            pst.setString(4, user.getName());
            pst.setInt(5, id);
            // 执行sql语句
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }








    public void delete(int id) {
        // 获得数据库连接对象
        Connection con = DBUtil.getCon();
        // 定义sql语句
        String sql = "delete from user where id=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            // 对SQL语句占位符赋值
            pst.setInt(1,id);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        // 获得数据库连接对象
        Connection con = DBUtil.getCon();
        // 定义sql语句
        String sql = "select * from user";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            // 查询操作
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // 如果查询到结果 初始化User对象 返回
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setGender(rs.getString("gender"));
                user.setAge(rs.getInt("age"));
                user.setLoginName(rs.getString("loginName"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
