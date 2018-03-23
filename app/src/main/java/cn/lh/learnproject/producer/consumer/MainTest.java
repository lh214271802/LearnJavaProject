package cn.lh.learnproject.producer.consumer;

/**
 * Created by liaohui on 2018/3/23.
 * 生产者消费者模式，解决线程同步可能造成死锁的问题
 * wait和notify都是用在线程同步之中的
 */

public class MainTest {
    public static void main(String[] args) {
        Movie movie = new Movie();
        Player p = new Player();
        Watcher w = new Watcher();
        p.m = movie;
        w.m = movie;
        new Thread(p).start();
        new Thread(w).start();
    }
}
