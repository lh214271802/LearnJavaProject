package cn.lh.learnproject.design_model.proxy_model.thread;

/**
 * Created by liaohui on 2018/3/21.
 * <p>
 * join 合并线程
 * yield 暂停线程
 */

public class JoinDemo extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("join thread--->" + i);
        }
    }

    public static void main(String[] args) {
        JoinDemo joinDemo = new JoinDemo();
        joinDemo.start();
        for (int i = 0; i < 100; i++) {
            if (i == 50) {
                try {
//                    yield();
                    //主线程阻塞,先等joinDemo线程运行完毕再接着运行
                    joinDemo.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main---->" + i);
        }
    }
}
