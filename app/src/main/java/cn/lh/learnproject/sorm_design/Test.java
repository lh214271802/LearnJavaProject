package cn.lh.learnproject.sorm_design;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import cn.lh.learnproject.sorm_design.util.JDBCUtil;

/**
 * SORM---simple Object relationship mapping
 * 简单对象关系映射
 * <p>
 * 一、从对象到sql：
 * 1.增加：讲对象对应成sql语句，执行sql，插入数据库中
 * 2.删除：根据对象主键的值，生成sql，执行，从库中删除
 * 3.修改：根据对象需要修改属性的值，生成sql，执行
 * 二、从sql到对象
 * 查询：根据结果进行分类
 */
public class Test {
    static {
        Connection mySqlConnection = null;
        try {
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dp.properties"));
            String mysqlDriver = properties.getProperty("driver");
            String mysqlURL = properties.getProperty("url");
            String mysqlUser = properties.getProperty("user");
            String mysqlPwd = properties.getProperty("pwd");
            mySqlConnection = JDBCUtil.getMySqlConnection(mysqlDriver, mysqlURL, mysqlUser, mysqlPwd);
        } catch (IOException e) {
            JDBCUtil.close(null, null, mySqlConnection);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }

}
