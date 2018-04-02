package cn.lh.learnproject.design_model.single;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by liaohui on 2018/4/2.
 * 测试反射和反序列化破解单例模式
 * 对枚举实现的单例不起作用，天然的单例
 */

@SuppressWarnings("all")
public class PokeSingleDemo {
    public static void main(String[] args) {
        SingleDemo s = SingleDemo.getInstance1();
        //1.通过反射方式直接调用私有构造器
        try {
            Class<SingleDemo> singleDemo = (Class<SingleDemo>) Class.forName("cn.lh.learnproject.design_model.single.SingleDemo");
            Constructor<SingleDemo> singleConstructor = singleDemo.getDeclaredConstructor(null);
            singleConstructor.setAccessible(true);
            SingleDemo singleInstance = singleConstructor.newInstance();
            System.out.println("反射方式结果,两个对象相等 s == singleInstance = " + (s == singleInstance));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //2.通过反序列化方式构造多个对象
        try {
            //序列化
            FileOutputStream outputStream = new FileOutputStream("F:/AllProject/mine/LearnJavaProject/a.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(s);
            objectOutputStream.close();
            outputStream.close();
            //反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("F:/AllProject/mine/LearnJavaProject/a.txt"));
            SingleDemo o = (SingleDemo) objectInputStream.readObject();
            System.out.println("序列化结果两个对象相等 s == o = " + (s == o));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
