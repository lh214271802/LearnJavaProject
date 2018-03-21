package cn.lh.learnproject.staticproxy.thread;

/**
 * Created by liaohui on 2018/3/21.
 */

public class MainDemo {
    public static void main(String[] args) {
        int num = 10;
        while (true) {
            System.out.println(num--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (num < 5) {
                break;
            }
        }
    }
}
