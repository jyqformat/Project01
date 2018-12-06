package Class;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) throws
            Exception{
        User u = new User();
        //定义查询条件的集合
        Map<String,Object> ifs = new HashMap<String,Object>();
        //假设 根据用户的名字 “张三” 查询
        ifs.put("name", "张三");
        EntityORMUtil util = new EntityORMUtilImpl();
        Connection conn = DBUtil.getCon();
        //将数据插入到数据库
        List users=util.getObjList(u, ifs, conn);
        for(int i=0;i<users.size();i++){
            User user = (User)users.get(i);
            System.out.println(user.getId()+":"
                    +user.getName()+":"+
                    user.getLoginName()+":"+
                    user.getGender()+":"+user.getAge());
        }
    }
}
