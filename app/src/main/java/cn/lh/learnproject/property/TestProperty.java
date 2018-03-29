package cn.lh.learnproject.property;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties 资源配置文件的读写
 * 1. key和value的值只能为字符串
 * 2. 存储与读取
 * Created by liaohui on 2018/3/19.
 */

public class TestProperty {
    public static void main(String[] args) {
        writeProperties();
        readProperties();

    }

    private static void readProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("app/src/db.properties"));
//            properties.load(TestProperty.class.getResourceAsStream("db.properties"));
//            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
            System.out.println(properties.get("url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeProperties() {
        Properties properties = new Properties();
        properties.setProperty("url", "https://www.baidu.com");
        properties.setProperty("user", "lh");
        properties.setProperty("pwd", "111111");
        String url = properties.getProperty("url", "default columnName");
        System.out.println(url);
        try {
            //存储到项目根目录下
            properties.store(new FileOutputStream(new File("app/src/db.properties")), "db配置");
            properties.storeToXML(new FileOutputStream(new File("app/src/db_xml.properties")), "db配置");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
