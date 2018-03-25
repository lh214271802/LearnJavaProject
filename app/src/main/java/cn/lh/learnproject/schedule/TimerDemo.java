package cn.lh.learnproject.schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liaohui on 2018/3/25.
 */

public class TimerDemo {

    private static int time;
    private static Timer timer;

    public static void main(String[] args) {
        timer = new Timer();
        time = 0;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time++;
                System.out.println("fuck you?");
                if (time >=4) {
                    timer.cancel();
                }
            }
        }, new Date(System.currentTimeMillis() + 1000), 1000);
    }
}
