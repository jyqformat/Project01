package XML;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONArraryDemo {
    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "张三");
        map1.put("gender", "男");
        map1.put("age", "23");
        JSONArray ja1 = JSONArray.fromObject(map1);
        System.out.println(ja1.toString());
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        JSONArray ja2 = JSONArray.fromObject(list);
        System.out.println(ja2.toString());
    }
}
