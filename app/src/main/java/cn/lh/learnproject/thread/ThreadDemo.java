package cn.lh.learnproject.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by liaohui on 2018/3/21.
 * 使用Callable创建线程
 */

public class ThreadDemo {
    public static void main(String[] args) {
        //创建线程
        ExecutorService service = Executors.newFixedThreadPool(2);
        //开两个线程,并获取值
        Race tortoise = new Race("乌龟", 1000, 0);
        Race rabbit = new Race("兔子", 2000, 0);
        Future result1 = service.submit(tortoise);
        Future result2 = service.submit(rabbit);
        try {
            Thread.sleep(2000);
            rabbit.setFlag(false);
            tortoise.setFlag(false);

            Object object1 = result1.get();
            Object object2 = result2.get();
            System.out.println("----乌龟111----->" + object1);
            System.out.println("----兔子222----->" + object2);

            //立即停止
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 创建异步线程的另外的方式
 * 模拟龟兔赛跑
 */
class Race implements Callable<Integer> {
    private String name;
    private int time;
    private boolean flag = true;
    private int step = 0;//步

    public Race(String name) {
        this.name = name;
    }

    public Race(String name, int time, int step) {
        this.name = name;
        this.time = time;
        this.step = step;
    }

    public Race() {
    }

    @Override
    public Integer call() throws Exception {
        while (flag) {
            Thread.sleep(time);//延时
            step++;
        }
        return step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}