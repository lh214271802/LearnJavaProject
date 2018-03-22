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
            synchronized (SingleDemo.class) {
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
