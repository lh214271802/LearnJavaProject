package cn.lh.learnproject.producer.consumer;

/**
 * 消费者
 * Created by liaohui on 2018/3/23.
 */

public class Watcher implements Runnable {
    public Movie m;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            m.watch();
        }
    }
}
