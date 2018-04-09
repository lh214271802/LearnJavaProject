package cn.lh.learnproject.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {

    public static void main(String[] args) {
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接(内部其实包含了Socket对象，是一个远程的链接，比较耗时)
            //真正开发中为提高效率使用连接池来管理连接对象
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:port/databaseName", "admin", "admin");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
