package DB;

import java.util.List;

public class MainDemo {
    public static void main(String[] args) {
        System.out.println(DBUtil.getCon());
        Batch.Batch();
//        User u = new User();
//        u.setLoginName("fs");
//        u.setPassword("123");
//        u.setGender("男");
//        u.setAge(18);
//        u.setName("帆神");
        UserDao dao = new UserDaoImpl();
//        dao.add(u);
        List<User> users = dao.findAll();
        for (User y : users){
            System.out.println(y.getId()+"\t"+y.getLoginName()+"\t"+y.getPassword()
                    +"\t"+y.getName()+"\t"+y.getAge()+"\t"+y.getGender());
        }

        System.out.println("Hello world");
//       dao.delete(1);


    }
}
