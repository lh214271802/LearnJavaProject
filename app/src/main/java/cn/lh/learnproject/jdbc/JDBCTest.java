package cn.lh.learnproject.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * sql语句操作
 */
public class JDBCTest {

    public static void main(String[] args) {
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接(内部其实包含了Socket对象，是一个远程的链接，比较耗时)
            //真正开发中为提高效率使用连接池来管理连接对象
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:port/databaseName", "admin", "admin");

            //这种情况可能存在SQL注入的危险
            Statement statement = connection.createStatement();
            String sql = "insert into tableName(user,pwd,time) values ('张三',123456,now())";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
