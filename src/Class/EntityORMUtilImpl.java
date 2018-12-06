package Class;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EntityORMUtilImpl implements EntityORMUtil{
    public int insertEntity(Connection conn, Object entity) throws Exception {
        if (conn == null) {
            return -1;
        }
        if (entity == null) {
            return -1;
        }
        // 获取表名
        String tableName = DOM4JUtil.getTableNameByEntity(entity.getClass());
        // 获取所有的属性Field
        Field[] fs = entity.getClass().getDeclaredFields();
        // 定义数组用来存放所有属性的值
        // 下面三个数组是一一对应，属性值-字段-属性类型
        Object[] fieldValues = new Object[fs.length];
        // 用来存放所有属性对应的字段
        String[] columnNames = new String[fs.length];
        // 用来存放属性的类型
        String[] columnTypes = new String[fs.length];
        // 定义StringBuffer动态生成SQL语句
        StringBuffer sb = new StringBuffer();
        // 首先拼接成插入的语法 insert into
        sb.append("insert into ");
        // 连接上表名
        sb.append(tableName);
        // 连接上"("
        sb.append("(");
        // 下面循环是判断entity那些属性值不为空，即是要插入值
        // 就说明哪些字段是需要插入值 在生成SQL语句就需要拼接上字段
        for (int i = 0; i < fs.length; i++) {
            fs[i].setAccessible(true);
            // 将属性的值放进对应的数组中
            fieldValues[i] = fs[i].get(entity);
            // 如果值不为空
            if (fieldValues[i] != null) {
                // 获取属性对应的字段名 并存放到对应的数组中
                columnNames[i] = DOM4JUtil.getColumnNameByFiled(
                        entity.getClass(), fs[i]);
                // 获取属性对应的类型，并存放到对应的数组对应的位置
                columnTypes[i] = DOM4JUtil.getColumnTypeByFiled(
                        entity.getClass(), fs[i]);
                // sql语句上拼接上该字段
                sb.append(columnNames[i] + ",");
            }
        }
        // 当程序执行到这一步生成sql语句应该是
        // insert into user(name,age,gender,
        // 所以需要把最后一个逗号去掉
        sb.deleteCharAt(sb.length() - 1);
        // 继续追加 ") values("
        sb.append(") values (");
        // 循环判断哪些属性不为空，就是需要拼接上一个"?"
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i] != null) {
                sb.append("?,");
            }
        }
        // 到此 SQL语句生成为:
        // insert into user(name,age,gender) values(?,?,?,
        // 去掉最后一个逗号
        sb.deleteCharAt(sb.length() - 1);
        // 拼接上最后一个括号,SQL语句生成完毕
        sb.append(")");
        // 打印输出到控制台
        System.out.println(sb.toString());
        PreparedStatement pstm = null;
        // 获取预编译对象 执行SQL语句
        pstm = conn.prepareStatement(sb.toString(),
                Statement.RETURN_GENERATED_KEYS);
        int k = 1;
        // 在执行SQL语句之前需要将SQL语句中的"?"设置值
        for (int i = 0; i < columnNames.length; i++) {
            // 设置值时需要判断对应的类型
            if (columnNames[i] != null) {
                if (columnTypes[i].equals("int")) {
                    pstm.setInt(k, (Integer) fieldValues[i]);
                } else if (columnTypes[i].equals("String")) {
                    pstm.setString(k, (String) fieldValues[i]);
                } else if (columnTypes[i].equals("Date")) {
                    java.sql.Date date = transferDate((java.util.Date) fieldValues[i]);
                    pstm.setDate(k, date);
                }
                k++;
            }
        }
        // 执行SQL语句
        pstm.executeUpdate();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    private java.sql.Date transferDate(java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = format.format(date);
        return java.sql.Date.valueOf(formatDate);
    }

    public int updateEntity(Connection conn, int id, Object entity)
            throws Exception {
        return 0;
    }

    public List<Object> getObjList(Object entity,
                                   Map<String, Object> ifs, Connection conn) throws Exception {
        // 获得表的名字
        String tableName = DOM4JUtil.getTableNameByEntity(entity.getClass());
        // 开始拼接sql语句
        StringBuilder sql = new StringBuilder("select * from ");
        // 拼接上表名
        sql.append(tableName + " ");
        List<Object> values = new ArrayList<Object>();
        // 判断查询的sql语句中是否有条件
        if (ifs.size() > 0) {

            // 如果有条件拼接上 where
            sql.append("where");
            // 获取条件的字段
            Iterator<String> keys = ifs.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                // 判断是否第一个条件 如果是第一个条件 直接拼接上 key=?
                if (sql.substring(sql.length() - 5, sql.length()).equals(
                        "where")) {
                    sql.append(" " + key + "=?");
                } else {
                    // 如果是第二个条件拼接上 and key=?
                    sql.append(" and " + key + "=?");
                }
                // 把条件的值存放到有序集合 values中
                values.add(ifs.get(key));
            }
        }
        ;
        // 获取预编译对象
        PreparedStatement pst = conn.prepareStatement(sql.toString());
        // 对SQL语句中的"?"赋值
        for (int i = 1; i <= values.size(); i++) {
            pst.setObject(i, values.get(i - 1));
        }
        // 执行SQL语句
        ResultSet rs = pst.executeQuery();
        // 将SQL语句输出到控制台
        System.out.println("sql语句" + sql.toString());
        // 方法返回的所有数据对象集合
        List<Object> objects = new ArrayList<Object>();
        // 获取对象的所有属性
        Field[] field = entity.getClass().getDeclaredFields();
        // 遍历结果集
        while (rs.next()) {
            // 通过反射创建出一个对象
            entity = Class.forName(entity.getClass().getName()).newInstance();
            // 将结果集中每一条结果集中的字段值 初始化对象的属性值
            for (int j = 0; j < field.length; j++) {
                // 获取属性的名字
                String name = field[j].getName();
                // 为后续的setXX提供基础
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                // 得到字段值
                String column = DOM4JUtil.getColumnNameByFiled(
                        entity.getClass(), field[j]);
                // 得到属性的类型
                String type = field[j].getGenericType().toString();
                if (type.equals("class java.lang.String")) {
                    // 得到属性对应的set方法
                    Method m = entity.getClass().getMethod("set" + name,
                            String.class);
                    // 执行该set方法对属性赋值
                    m.invoke(entity, rs.getString(column));

                }
                if (type.equals("class java.lang.Integer")) {
                    Method m = entity.getClass().getMethod("set" + name,
                            Integer.class);
                    m.invoke(entity, rs.getInt(column));
                }

                if (type.equals("class java.util.Date")) {
                    Method m = entity.getClass().getMethod("set" + name,
                            Date.class);
                    m.invoke(entity, rs.getDate(column));
                }
            }
            // 将初始化的一个对象添加到集合中
            objects.add(entity);
        }
        return objects;

    }

    public int delete(Connection conn, int id, Object obj) throws Exception {
        return 0;
    }
}
