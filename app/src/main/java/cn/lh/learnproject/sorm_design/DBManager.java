package cn.lh.learnproject.sorm_design;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 */
public class DBManager {
    private static Configuration conf;

    static {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
            conf = new Configuration(properties.getProperty("driver"),
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("pwd"),
                    properties.getProperty("usingDB"),
                    properties.getProperty("srcPath"),
                    properties.getProperty("poPackage"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName(conf.getDriver());
            //直接建立连接，后期增加连接池处理，提高效率
            return DriverManager.getConnection(conf.getUrl(),
                    conf.getUser(),
                    conf.getPwd());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
