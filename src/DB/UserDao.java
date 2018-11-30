package DB;

import java.util.List;

public interface UserDao {
    //添加
    public void add(User user);
    //修改
    public void update(int id,User user);
    //删除
    public void delete(int id);
    //查询
    public List<User> findAll();



}
