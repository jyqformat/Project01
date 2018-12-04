package DB;

import java.sql.*;

public class pageSize {
    public static void getPage(int pageSize, int page) {
        int begin = (page-1)*pageSize+1;
        String sql = "select * from user";
        try {
            Connection con = DBUtil.getCon();
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs = stmt.executeQuery(sql);
            rs.absolute(begin);
            for(int i = 0;i<pageSize;i++) {
                System.out.println(rs.getInt("id"));
                if(!rs.next()) {
                    break;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void PageSearch (int pageSize,int page) {
        int begin = (page-1)*pageSize+1;
        int end = begin+pageSize-1;
        String sql = "select * from user limit 21,10";
        Connection con = DBUtil.getCon();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, begin);
            pst.setInt(2, end);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getInt("id"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static int chekUser(int id, String pwd) {
        Connection con = DBUtil.getCon();
        String sql = "{call checkUser(?,?,?)}";
        CallableStatement cst;
        int flag = 0;
        try {
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            cst.setString(2, pwd);
            cst.registerOutParameter(3, Types.INTEGER);
            cst.execute();
            flag = cst.getInt(3);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

}
