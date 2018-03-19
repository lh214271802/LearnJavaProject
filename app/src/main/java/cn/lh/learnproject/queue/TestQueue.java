package cn.lh.learnproject.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by liaohui on 2018/3/19.
 */

public class TestQueue {
    public static void main(String[] args) {

        testQueue();
    }

    //队列，先进先出
    private static void testQueue() {
        Queue<Request> queue = new ArrayDeque<>();
        //模拟排队情况
        for (int i = 0; i < 10; i++) {
            final int num = i;
            queue.offer(new Request() {
                @Override
                public void deposit() {
                    System.out.println("第" + num + "个人,办理存款额度" + (Math.random() * 10000));
                }
            });
        }

        Request request = null;
        while (null != (request = queue.poll())) {
            request.deposit();
        }
    }
}

interface Request {
    //存款
    void deposit();
}
