package cn.lh.learnproject.producer.consumer;

/**
 * 生产者
 * Created by liaohui on 2018/3/23.
 */

public class Player implements Runnable {
    public Movie m;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (0==i%2){
                m.play("左青龙");
            }else {
                m.play("右白虎");
            }
        }
    }
}
