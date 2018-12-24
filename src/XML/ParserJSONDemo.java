package XML;

import net.sf.json.JSONArray;

public class ParserJSONDemo {
    public static void main(String[] args) {
        String str = "[{'ID':1,'age':'23','name':'张三','gender':'男'}]";
        JSONArray ary = JSONArray.fromObject(str);
        for (int i = 0; i < ary.size(); i++) {
            Student student = new Student();
            student.setName(ary.getJSONObject(i).getString("name"));
            student.setGender(ary.getJSONObject(i).getString("gender"));
            student.setAge(ary.getJSONObject(i).getString("age"));
            student.setId(ary.getJSONObject(i).getString("ID"));
            System.out.println(student.getId() + ":" + student.getName() + ":" +
                    student.getGender() + ":" + student.getAge());
        }
    }
}
