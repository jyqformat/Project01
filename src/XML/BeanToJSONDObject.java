package XML;

import net.sf.json.JSONObject;

public class BeanToJSONDObject {
    public static void main(String[] args) {
        Student stu = new Student();
        stu.setId("1");
        stu.setName("张三");
        stu.setAge("18");
        stu.setGender("男");
        JSONObject jsonObject = JSONObject.fromObject(stu);
        System.out.println(jsonObject.toString());
    }
}
