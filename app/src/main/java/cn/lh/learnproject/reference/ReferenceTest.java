package cn.lh.learnproject.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by liaohui on 2018/3/19.
 * 强、软、弱、虚四种引用
 */

public class ReferenceTest {
    public static void main(String[] args) {
        testSoft();
        testWeak();
    }

    public static void testWeak() {
        System.out.println("******************************************************************");
        //字符串常量池 共享（不能回收）
        String str1 = "春天在哪里?";
        //
        String str2 = new String("春天在哪里?");
        //弱引用管理对象
        WeakReference<String> wr1 = new WeakReference<String>(str1);
        WeakReference<String> wr2 = new WeakReference<String>(str2);
        System.out.println("gc运行前-str1->" + wr1.get());
        System.out.println("gc运行前-str2->" + wr2.get());
        //断开引用
        str1 = null;
        str2 = null;
        //通知回收
        System.gc();
        System.runFinalization();
        System.out.println("gc运行后-str1->" + wr1.get());
        System.out.println("gc运行后-str2->" + wr2.get());

        System.out.println("******************************************************************");
        Object obj = new Object();
        ReferenceQueue rq = new ReferenceQueue<>();
        WeakReference wr = new WeakReference(obj, rq);
        System.out.println(wr.get());
        System.out.println(rq.poll());
        obj = null;
        System.gc();
        System.runFinalization();
        //因为finalizer线程优先级很低，所以让线程等待200ms
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //这时候会输出null
        System.out.println(wr.get());
        //rq队列里也会存放这个弱引用，输出它的hashcode
        System.out.println(rq.poll());
    }

    public static void testSoft() {
        Object obj = new Object();
        ReferenceQueue rq = new ReferenceQueue<>();
        //创建关于obj的软引用，使用引用队列
        SoftReference sr = new SoftReference(obj, rq);
        System.out.println(sr.get());
        //get方法会输出这个obj对象的hashcode
        System.out.println(rq.poll());
        //输出为null
        obj = null;
        System.gc();
        System.runFinalization();
//        //因为finalizer线程优先级很低，所以让线程等待200ms
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(sr.get());
        //因为堆空间没满，可有可无的特性，所以还是会输出这个obj对象的hashcode

        //自然队列为null
        System.out.println(rq.poll());
    }
}
