package Class;

import DB.User;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) throws Exception{
        User user = new User();
        user.setLoginName("zhangliu");
        user.setAge(22);
        EntityORMUtil util = new EntityORMUtilImpl();
        Connection connection = DBUtil.getCon();
        util.insertEntity(connection,user);
    }
}
