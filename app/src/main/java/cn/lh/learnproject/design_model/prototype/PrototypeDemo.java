package cn.lh.learnproject.design_model.prototype;

import android.annotation.SuppressLint;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * 原型模式
 * 普通new方式创建对象和clone方式创建对象的效率差异：如果
 * 需要短时间创建大量对象，并且new的过程比较耗时，则可以考虑原型模式（clone）
 */
public class PrototypeDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        //浅克隆
        simpleClone();
        System.out.println("=======================================================================================================");
        //深克隆
        deepClone();
        System.out.println("=======================================================================================================");
        //序列化和反序列化实现深克隆
        deepClone2();

        System.out.println("=======================================================================================================");
        //当new对象需要的操作太多，太耗时的话，提倡使用clone
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            MySheep sheep = new MySheep("草泥马", new Date(1102535656010L));
        }
        long time2 = System.currentTimeMillis();
        System.out.println("new方式创建对象耗时" + (time2 - time1));

        long time3 = System.currentTimeMillis();
        MySheep sheep = new MySheep("草泥马", new Date(1102535656010L));
        for (int i = 0; i < 1000; i++) {
            MySheep temp = (MySheep) sheep.clone();
        }
        long time4 = System.currentTimeMillis();
        System.out.println("clone方式创建对象耗时" + (time4 - time3));
    }


    private static void simpleClone() {
        try {
            Sheep sheep = new Sheep("少利", new Date(1102535656010L));
            System.out.println(sheep);
            Sheep cloneSheep = (Sheep) sheep.clone();
            sheep.birthday.setTime(1202535656010L);
            System.out.println(cloneSheep);

            //因为是浅克隆，所以会有克隆对象的内部其他属性指向的还是同一个对象
            System.out.println(sheep.birthday == cloneSheep.birthday);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static void deepClone() {
        try {
            MySheep sheep = new MySheep("少利", new Date(1102535656010L));
            System.out.println(sheep);
            MySheep cloneSheep = (MySheep) sheep.clone();
            sheep.birthday.setTime(1202535656010L);
            System.out.println(cloneSheep);

            //因为是深克隆，所以会有克隆对象的内部其他属性指向的不会是同一个对象
            System.out.println(sheep.birthday == cloneSheep.birthday);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static void deepClone2() {
        try {
            Sheep sheep = new Sheep("少利", new Date(1102535656010L));
            System.out.println(sheep);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(sheep);
            byte[] bytes = baos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Sheep cloneSheep = (Sheep) ois.readObject();
            sheep.birthday.setTime(1202535656010L);
            System.out.println(cloneSheep);

            //因为是深克隆
            System.out.println(sheep.birthday == cloneSheep.birthday);
            ois.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

//1997年，英国的克隆羊，多利！
//浅克隆
@SuppressLint("NewApi")
class Sheep implements Cloneable, Serializable {

    public String name;
    public Date birthday;

    public Sheep(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //直接调用Object的clone方法
        Object obj = super.clone();
        return obj;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}' + "address====>" + super.toString();
    }
}

//深克隆
@SuppressLint("NewApi")
class MySheep implements Cloneable {

    public String name;
    public Date birthday;

    public MySheep(String name, Date birthday) {
        try {
            //模拟创建对象的耗时操作
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //直接调用Object的clone方法
        Object obj = super.clone();
        //实现如下代码实现深复制deep clone
        MySheep mySheep = (MySheep) obj;
        mySheep.birthday = (Date) this.birthday.clone();
        return obj;
    }

    @Override
    public String toString() {
        return "MySheep{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}' + "address====>" + super.toString();
    }
}