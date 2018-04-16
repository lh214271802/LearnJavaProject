package cn.lh.learnproject;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class MyTest {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {
//        testStringValue();
//        testLoop();
//        testString();


//        testList();
//        testHeap();

//        testTime();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void testTime() {
        System.out.println(System.currentTimeMillis());
        Date date = new Date();
        System.out.println(date);
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTimeInMillis());
        System.out.println(calendar);
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);


        System.out.println(Clock.systemDefaultZone().millis());
    }


    interface A {
    }

    interface B {
    }

    interface C {
    }

    interface D extends A, B, C {
    }

    class F<T extends A & B & C> {
    }


    private static void testHeap() {
        //String s = new String("xyz");创建了两个对象，一个是静态区的"xyz"，一个是用new创建在堆上的对象
        String aa = "你好啊";
        String bb = new String("你好啊");
        System.out.println("aa == bb.intern() = " + (aa == bb.intern()) + "\naa == bb = " + (aa == bb));
    }

    /**
     * list随机排序
     */
    private static void testList() {
        ArrayList<String> list = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
            add("d");
            add("e");
        }};
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
    }

    /**
     * 1. String对象的intern方法会得到字符串对象在常量池中对应的版本的引用（如果常量池中有一个字符串与String对象的equals结果是true），
     * 如果常量池中没有对应的字符串，则该字符串将被添加到常量池中，然后返回常量池中字符串的引用；
     * 2. 字符串的+操作其本质是创建了StringBuilder对象进行append操作，然后将拼接后的StringBuilder对象用toString方法处理成String对象，
     * 这一点可以用javap -c StringEqualTest.class命令获得class文件对应的JVM字节码指令就可以看出来
     */
    private static void testString() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s5);//true
        System.out.println(s1 == s6);//false  todo why?
        System.out.println(s1 == s6.intern());//true
        System.out.println(s2 == s2.intern());//false
    }

    private static void testLoop() {
        A:
        for (int i = 0; i < 10; i++) {
            B:
            for (int j = 0; j < 10; j++) {
                System.out.println("B循环啊-----------------------》" + j);
                if (j == 5) {
                    //此处对比break;以及break A;
                    //默认的break是终止内层循环，break A是终止了所有循环
                    break;
                }
            }
            System.out.println("A循环啊----------------》" + i);
        }
    }


    /**
     * 解释内存中的栈(stack)、堆(heap)和方法区(method area)的用法。
     * 答：通常我们定义一个基本数据类型的变量，一个对象的引用，还有就是函数调用的现场保存都使用JVM中的栈空间；
     * 而通过new关键字和构造器创建的对象则放在堆空间，堆是垃圾收集器管理的主要区域，由于现在的垃圾收集器都采用分代收集算法，
     * 所以堆空间还可以细分为新生代和老生代，再具体一点可以分为Eden、Survivor（又可分为From Survivor和To Survivor）、Tenured；
     * 方法区和堆都是各个线程共享的内存区域，用于存储已经被JVM加载的类信息、常量、静态变量、JIT编译器编译后的代码等数据；
     * 程序中的字面量（literal）如直接书写的100、"hello"和常量都是放在常量池中，常量池是方法区的一部分，
     * 。栈空间操作起来最快但是栈很小，通常大量的对象都是放在堆空间，栈和堆的大小都可以通过JVM的启动参数来进行调整，
     * 栈空间用光了会引发StackOverflowError，而堆和常量池空间不足则会引发OutOfMemoryError。
     * <p>
     * String str = new String("hello");
     * 1
     * 上面的语句中变量str放在栈上，用new创建出来的字符串对象放在堆上，而"hello"这个字面量是放在方法区的。
     * <p>
     * 补充1：较新版本的Java（从Java 6的某个更新开始）中，由于JIT编译器的发展和"逃逸分析"技术的逐渐成熟，
     * 栈上分配、标量替换等优化技术使得对象一定分配在堆上这件事情已经变得不那么绝对了。
     * <p>
     * 补充2：运行时常量池相当于Class文件常量池具有动态性，Java语言并不要求常量一定只有编译期间才能产生，
     * 运行期间也可以将新的常量放入池中，String类的intern()方法就是这样的。
     */
    private static void testStringValue() {
        String s1 = new StringBuilder("go")
                .append("od").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder("ja")
                .append("va").toString();
        System.out.println(s2.intern() == s2);

        String s3 = new StringBuilder("hel")
                .append("lo").toString();
        System.out.println(s3.intern() == s3);


        Integer a = 6;
        Integer b = 6;
        //Integer内部自动调用valueOf方法进行赋值，
        //    public static Integer valueOf(int i) {
        //        if (i >= IntegerCache.low && i <= IntegerCache.high)
        //            return IntegerCache.cache[i + (-IntegerCache.low)];
        //        return new Integer(i);
        //    }
        Integer c = 129;
        Integer d = 129;
        System.out.println(a == b);
        System.out.println(c == d);
    }
}
