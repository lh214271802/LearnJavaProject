package cn.lh.learnproject.producer.consumer;

/**
 * Created by liaohui on 2018/3/23.
 */

public class Movie {
    public String pic;

    //信号灯  true 生产，消费者等待，生产完，通知消费
    //false 消费，生产者等待,消费完通知生产
    public boolean isNeedShengChan = true;

    public synchronized void play(String pic) {
        if (!isNeedShengChan) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //开始生产
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //生产完毕
        this.pic = pic;
        //生产者停下，并通知消费者消费
        this.notify();
        this.isNeedShengChan = false;
        System.out.println("生产了" + pic);
    }

    public synchronized void watch() {
        if (isNeedShengChan) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //开始消费
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //消费完毕，通知生产
        this.notify();
        //停止消费
        this.isNeedShengChan = true;
        System.out.println("消费了" + pic);
    }
}
