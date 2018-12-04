package DB;

import java.util.List;

public class MainDemo {
    public static void main(String[] args) {
        // 在控制台打印JDBC连接信息
        System.out.println(DBUtil.getCon());

        // 通过JDBC一系列的操作
//        Batch.Batch();  // 批处理
        Commit.Commit();  // 事物操作
//        User u = new User(); // 插入一条
//        u.setLoginName("fs");
//        u.setPassword("123");
//        u.setGender("男");
//        u.setAge(18);
//        u.setName("帆神");
        UserDao dao = new UserDaoImpl();
//        dao.add(u);
//        dao.delete(1);

        //输出到控制台
        List<User> users = dao.findAll();
        for (User y : users){
            System.out.println(y.getId()+"\t"+y.getLoginName()+"\t"+y.getPassword()
                    +"\t"+y.getName()+"\t"+y.getAge()+"\t"+y.getGender());
        }

        System.out.println("Hello world");



    }
}
