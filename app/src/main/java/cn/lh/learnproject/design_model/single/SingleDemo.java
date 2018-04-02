package cn.lh.learnproject.design_model.single;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by liaohui on 2018/3/22.
 * 懒汉式
 * 过多的同步方法可能会造成死锁，解决方案：生产者消费者模式
 */

public class SingleDemo implements Serializable {

    public static SingleDemo instance = null;

    //TODO 修复反射创建对象的方法
    private SingleDemo() {
        //防止反射方式破解单例
        throw new UnsupportedOperationException("你不可以创建哦！");
    }


    //TODO 修复反序列化创建对象的方法
    //反序列化时,如果定义了readResolve方法则直接返回此方法置顶的对象，而不需要单独在创建对象
    private Object readResolve() throws ObjectStreamException {
        return instance;
    }


    //double checking
    public static SingleDemo getInstance1() {
        if (instance == null) {//加上这个是为了提高效率，不用每次都进入synchronized代码块
            synchronized (SingleDemo.class) {//安全
                if (instance == null) {
                    instance = new SingleDemo();
                }
            }
        }
        return instance;
    }


    public static synchronized SingleDemo getInstance2() {
        if (instance == null) {
            instance = new SingleDemo();
        }
        return instance;
    }
}

//饿汉式  类加载时就加载了instance
class SingleDemo2 {
    //类加载(ClassLoader加载)时，天然是线程安全的，所以下面的获取实例的方法不需要加同步块synchronized
    public static SingleDemo2 instance = new SingleDemo2();

    private SingleDemo2() {
    }

    public static SingleDemo2 getInstance() {
        return instance;
    }
}

//饿汉式   在使用SingleDemo3Holder的时候才会加载instance(调用getInstance的时候)
class SingleDemo3 {

    //静态内部类实现的单例模式：线程安全，调用效率高，且实现了延时加载！
    private static class SingleDemo3Holder {
        private static final SingleDemo3 instance = new SingleDemo3();
    }

    private SingleDemo3() {
    }

    public static SingleDemo3 getInstance() {
        return SingleDemo3Holder.instance;
    }
}

//枚举方式实现单例模式,并且可以天然的放置反射和反序列化漏洞
enum SingleDemo4 {
    /**
     * 定义一个枚举的元素，它就代表了Singleton的一个实例;这个枚举元素，本身就是单例对象
     * 无延时加载效果
     */
    INSTANCE;

    /**
     * 单例可以有自己的操作,添加自己的一些功能处理
     */
    public void signgleOperation() {
        //功能处理
    }
}

class SingleDemoTest {
    public static void main(String[] args) {
        //总共5种创建单例的方式
        SingleDemo s1 = SingleDemo.getInstance1();
        SingleDemo s11 = SingleDemo.getInstance2();
        SingleDemo2 s2 = SingleDemo2.getInstance();
        SingleDemo3 s3 = SingleDemo3.getInstance();
        SingleDemo4 s4 = SingleDemo4.INSTANCE;
        System.out.println(s1 == s11);
    }
}