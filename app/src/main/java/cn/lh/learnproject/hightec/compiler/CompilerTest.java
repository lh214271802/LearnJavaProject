package cn.lh.learnproject.hightec.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 动态编译
 */
public class CompilerTest {


    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        try {
            URL[] urls = {new URL("file:/" + "F:/ttt/")};
            URLClassLoader loader = new URLClassLoader(urls);
            Class<?> c = loader.loadClass("HelloWorld");
            Method main = c.getMethod("main", String[].class);
            main.invoke(null, (Object) new String[]{"aa", "bb"});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        try {
            //新开一个进程去编译
            Runtime run = Runtime.getRuntime();
            Process process = run.exec("java -cp f:/ttt    HelloWorld.java");
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String info = "";
            if (null != (info = reader.readLine())) {
                System.out.println(info);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
