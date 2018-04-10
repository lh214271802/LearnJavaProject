package cn.lh.learnproject.jdbc;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * sql语句操作
 */
public class JDBCTest {

    public static void main(String[] args) {
        testSql1();
        testSql2();
        //事务：一组要么同时执行成功要么同时执行失败的SQL语句，是数据库操作的一个执行单元
        //事务的四大特点：原子性(要么全部成功要么全部失败)、
        //一致性(一个操作失败时，所有更改过的数据全部回滚到修改前的状态)、
        //隔离性(事务查看数据时数据所处的状态要么是另一并发事务修改它之前的状态，要么是另一事务修改它之后的状态，事务不会去查看中间状态的数据)、
        //持久性(持久性事务完成之后，对于系统的影响是永久性的)
        testSqlConstraction();

    }

    //测试事务
    private static void testSqlConstraction() {
        Connection connection = null;
        //批处理最好用Statement，以免异常
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接(内部其实包含了Socket对象，是一个远程的链接，比较耗时)
            //真正开发中为提高效率使用连接池来管理连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:port/databaseName", "admin", "admin");

            connection.setAutoCommit(false);//设置为手动提交

            String sql = "insert into tableName(user,pwd) values (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "username");
            preparedStatement.setObject(2, "password");
            preparedStatement.execute();
            System.out.println("插入一个用户");

            String sql2 = "insert into tableName(user,pwd) values (?,?)";
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setObject(1, "username");
            preparedStatement2.setObject(2, "password");
            preparedStatement2.execute();


            //文本大对象的使用（Clob）插入---如文件存储
            String sql3 = "insert into tableName(userName,password) values (?,?)";
            preparedStatement3 = connection.prepareStatement(sql3);
            preparedStatement3.setString(1, "asdopashg");
            //文件内容插入，就是把文本文件内容输入到数据库中
            preparedStatement3.setClob(2, new FileReader(new File("d://a.txt")));
//            preparedStatement3.setClob(2, new InputStreamReader(new ByteArrayInputStream("dasgiophasihih".getBytes())));
            preparedStatement3.execute();


            //二进制大对象的使用（Blob）插入---如图片存储
            String sql4 = "insert into tableName(userName,password) values (?,?)";
            preparedStatement4 = connection.prepareStatement(sql4);
            preparedStatement4.setString(1, "asdopashg");
            //二进制内容插入，就是把文件内容转为二进制流输入到数据库中
            preparedStatement4.setBlob(2, new FileInputStream("d://icon.jpg"));
            preparedStatement4.execute();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement4 != null)
                    preparedStatement4.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement3 != null)
                    preparedStatement3.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement2 != null)
                    preparedStatement2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //批处理
    private static void testSql2() {
        Connection connection = null;
        //批处理最好用Statement，以免异常
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接(内部其实包含了Socket对象，是一个远程的链接，比较耗时)
            //真正开发中为提高效率使用连接池来管理连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:port/databaseName", "admin", "admin");

            connection.setAutoCommit(true);//设置为自动提交,默认也是自动的

            statement = connection.createStatement();
            //插入2万条数据
            for (int i = 0; i < 20000; i++) {
                statement.addBatch("insert into tableName(user,pwd,time) values ('张" + i + "',123456,now())");
            }
            statement.executeBatch();
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //先打开的后关闭
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testSql1() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接(内部其实包含了Socket对象，是一个远程的链接，比较耗时)
            //真正开发中为提高效率使用连接池来管理连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:port/databaseName", "admin", "admin");

//            String sql = "insert into tableName(user,pwd,time) values ('张三',123456,now())";
            //这种情况可能存在SQL注入的危险  可以使用PreparedStatement来防止这种情况
//            Statement statement = connection.createStatement();
//            statement.execute(sql);

            //下面的写法就可以预防SQL注入
            String sql = "insert into tableName(user,pwd,time) values (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            //这里的参数索引是从1开始计算，不是从0开始
            preparedStatement.setString(1, "userName");
            preparedStatement.setString(2, "passWord");
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
            //效果和下面这个的效果相同,下面这个不区分参数类型
//            preparedStatement.setObject(1,"userName");
//            preparedStatement.setObject(2,"passWord");
//            preparedStatement.setObject(3, new Date(System.currentTimeMillis()));
            preparedStatement.execute();
            //下面这个也可以提交修改(insert/update/delete)，返回数据不过是更新的行数
//            int updateCount = preparedStatement.executeUpdate();
            //如果是查询语句的话,下面的语句可以返回查询到的数据
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //得到查询到的数据
                System.out.println(resultSet.getInt(1) + "---" + resultSet.getString(2) + "---" + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //先打开的后关闭
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

