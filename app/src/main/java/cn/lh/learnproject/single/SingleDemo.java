package cn.lh.learnproject.single;

/**
 * Created by liaohui on 2018/3/22.
 * 懒汉式
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
        private static SingleDemo3 instance = new SingleDemo3();
    }

    private SingleDemo3() {
    }

    public static SingleDemo3 getInstance() {
        return SingleDemo3Holder.instance;
    }
}