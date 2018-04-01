package cn.lh.learnproject.design_model.single;

/**
 * Created by liaohui on 2018/3/22.
 * 懒汉式
 * 过多的同步方法可能会造成死锁，解决方案：生产者消费者模式
 */

public class SingleDemo {

    public static SingleDemo instance = null;

    private SingleDemo() {
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

    private static class SingleDemo3Holder {
        private static final SingleDemo3 instance = new SingleDemo3();
    }

    private SingleDemo3() {
    }

    public static SingleDemo3 getInstance() {
        return SingleDemo3Holder.instance;
    }
}