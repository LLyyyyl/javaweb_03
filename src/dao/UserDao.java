package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import vo.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static User select(String userName, String password) throws Exception{//查询用户信息
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8",
                "root","Lyl99999");

        String sql1="select * from t_user";
        PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql1);
        ResultSet rs=(ResultSet) ps.executeQuery();

        while(rs.next()) {
            String name=rs.getString("userName");
            String word=rs.getString("password");

            if(name.equals(userName) && word.equals(password)) {
                String chrName=rs.getString("chrName");
                String role=rs.getString("role");
                User user=new User(name,word,chrName,role);
                return user;
            }
        }

        ps.close();
        rs.close();
        con.close();

        return null;
    }

    public User get(String userName) throws SQLException, ClassNotFoundException {
        User user = null;

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8",
                "root","Lyl99999");

        String sql = "select * from t_user where username=?";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            String name=rs.getString("userName");
            String chrName=rs.getString("chrName");
            String word=rs.getString("password");
            String role=rs.getString("role");
            user = new User(name,chrName,word,role);
        }

        ps.close();
        rs.close();
        con.close();

        return user;
    }




}
