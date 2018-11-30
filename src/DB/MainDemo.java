package DB;

public class MainDemo {
    public static void main(String[] args) {
        System.out.println(DBUtil.getCon());
        User u = new User();
        u.setLoginName("fs");
        u.setPassword("123");
        u.setGender("男");
        u.setAge(18);
        u.setName("帆神");
        UserDao dao = new UserDaoImpl();
       dao.add(u);

//       dao.delete(1);


    }
}
