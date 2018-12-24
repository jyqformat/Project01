package XML;

import net.sf.json.JSONObject;

public class JSONBuilderDemo {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID", 1);
        jsonObject.put("name", "张三");
        jsonObject.put("gender", "男");
        jsonObject.put("age", 18);
        System.out.println(jsonObject.toString());
    }
}
