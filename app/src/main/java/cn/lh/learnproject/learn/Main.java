package cn.lh.learnproject.learn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Main {

    private static int fuck = 0x3523;
    private static int fuck1 = 03523;
    private static int binaryInt = 0b0000_1010_0010_0101;

    /**
     * 默认的成员变量int为0，boolean为false，char\u0000，对象为null
     */
    public static void main(String[] args) {
//        mathTest();
//        objectTest();
//        arrayTest();
//        stringTest();
//        stringBuilderTest();
//        wrapperClassTest();
//        testDate();
//        testCalendar();
//        testFile();
//        testCollection();
//        testMyList();
        testMap();
    }

    private static void testMap() {
        Map map = new HashMap<>();//非线程安全，效率高
        Map hashtable = new Hashtable();//线程安全，效率低
        map.put("aghoig", "agjpojapogjuo sagj是破碎");
        map.put(1, "哈哈");
        map.put(2, "哈哈刚刚");
        map.put(2, "哈桑田哈");
        System.out.println(map.get(1) + "---" + map.get(2));
        System.out.println();
        Set set = new HashSet();//内部使用HashMap实现,无序
        set.add('a');
        set.add("副噶破坏");
        set.add('G');
        set.add("副驾驶的彷徨");
        set.add(2);
        set.add('c');
        set.add(24);
        set.add("副gahog");
        set.add("估计是跑");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
    }

    private static void testMyList() {
        //暂时重写了部分方法，可以完善
        MyArrayList list = new MyArrayList(3);
        list.add("shaioagghs");
        list.add("fsdfs");
        list.add(1);
        list.add("shaioghs");
        list.add("agdahg");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));

    }

    private static void testString() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();


        //ArrayList（见源码，数组实现，查询效率高）和LinkedList（见源码，节点实现，增删改效率高）线程不安全，效率高
        //Vector数组实现的，线程安全效率低
        List<String> list = new ArrayList<String>();
        List<String> list2 = new LinkedList<>();
        Vector<String> vector = new Vector<>();
        list.add("aaa");
    }

    /**
     * String和StringBuilder类中都是由char[]来保存数据的，
     * 不过String中的char[]为final变量，故而String为不可变字符序列，
     * StringBuilder为可变字符序列
     */
    private static void test1() {
        char i = '廖';
        char j = '辉';
        String x = "" + i + j;
        System.out.println(x + ((int) i) + "fuck" + ((int) j) + ((char) 22659));

        /**下划线可以作为数字的分割线，便于查看，无实际意义*/
        int a = 0b0000_0000_0000_0000_0000_0000_0000_0011;
        int b = 1_24_456_3355;
        System.out.println(a + "------" + b);
        int ss = 1244 * 245 * 256;

        //这里一定是在ss前面加(float)才能正确得到答案，没有加的话会超出int的取值范围而得到错误的值，如果是把
        //(float)后面计算的值全部扩起来的话也是错误的值，因为已经超过了int的取值范围
        float ff = (float) ss * 2364 * 255 * 255;
        //float ff = (float) (ss * 2364 * 255 * 255);//错误
        //float ff = ss * 2364 * 255 * 255;//错误
        System.out.println(ff);
    }

    /**
     * @param a
     * @param b
     * @param agag
     * @return
     */
    public static int fuck(int a, int b, String agag) {
        return 0;
    }

    private static void testCollection() {
        List c = new ArrayList<String>();
        List d = new LinkedList();
        c.add("chengDu");
        System.out.println(c.iterator().next());

        List mLinkedList = new MyLinkedList();
        mLinkedList.add("ahgoiasog");
        mLinkedList.add("chengdu");
        mLinkedList.add(35f);
        mLinkedList.add(2, "春天");
        mLinkedList.set(2, "在哪里");
        mLinkedList.remove(0);
        System.out.println(mLinkedList.get(0));
        System.out.println(mLinkedList.get(1));
        System.out.println(mLinkedList.get(2));
//        System.out.println(mLinkedList.get(3));
    }

    private static void testFile() {
        File file1 = new File("F://project");
        System.out.println(file1.exists());

        //最后修改时间
        Date d = new Date(file1.lastModified());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd\tHH:mm:ss").format(d));
        System.out.println("isFile--->" + file1.isFile() + "--isDirectory--->" + file1.isDirectory());
        FileReader fileReader = null;
        InputStream in = null;
        try {
            fileReader = new FileReader("F://project/myJava/test.txt");
            char read = (char) fileReader.read();
            System.out.println(read);

            char[] buf = new char[1024];
            StringBuilder stringBuilder = new StringBuilder();
            if (-1 != fileReader.read(buf)) {
                stringBuilder.append(buf);
            }
            System.out.println(new String(stringBuilder.toString().getBytes(), "UTF-8"));
            in = new FileInputStream("F://project/myJava/test.txt");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (fileReader != null) fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testCalendar() {
        String time = "2018-3-13";
        Calendar calendar = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(time);
            calendar.setTime(date);
            //当前月份当前日期的天数
            int currentDate = calendar.get(Calendar.DAY_OF_MONTH);
            //设置为当前月份的第一天
            calendar.set(Calendar.DATE, 1);
            //得到当前月份的最后一天,也就是最大天数
            int maxDate = calendar.getActualMaximum(Calendar.DATE);
            System.out.println("日\t一\t二\t三\t四\t五\t六");
            //打印出1号以前的空格，也就是当前1号星期几到星期日之间的空格天数
            for (int i = 1; i < calendar.get(Calendar.DAY_OF_WEEK); i++) {
                System.out.print("\t");
            }
            for (int i = 1; i <= maxDate; i++) {
                if (currentDate == i) {
                    System.out.print(i + "*\t");
                } else {
                    System.out.print(i + "\t");
                }
                int w = calendar.get(Calendar.DAY_OF_WEEK);
                if (w == Calendar.SATURDAY) {
                    System.out.println();
                }
                //每打印一天就加一天的天数
                calendar.add(Calendar.DATE, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {

        }


        calendar.set(2018, 2, 12, 23, 57);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
    }

    private static void testDate() {
        Date date = new Date(1222);
        System.out.println(date.toGMTString());
//        DateFormat dateFormat = DateFormat.getDateInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd\tHH:mm:ss");
        Date date1 = new Date();
        String format = simpleDateFormat.format(date1);
        System.out.println(format);

        Calendar calendar = new GregorianCalendar();

        System.out.println(calendar);
    }

    /**
     * 包装类
     */
    private static void wrapperClassTest() {
        Integer a = new Integer("32");
        Integer b = 235;
        int c = new Integer(45);
        System.out.println(Integer.parseInt("2541", 8));
    }

    /**
     * String是不可变字符序列
     * 可变字符序列:StringBuilder(线程不安全，效率高)和StringBuffer(线程安全，效率低)
     */
    private static void stringBuilderTest() {
        Object[] ints = new Integer[]{1, 2, 2, 354, 56, 7, 78, 2, 74, 3};
        List<Integer> integers = Arrays.asList((Integer[]) ints);
        System.out.println(integers);
        System.out.println(Arrays.toString(ints));
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
        List list = new ArrayList<>();
        list.add("afbas");
        list.add(new Car());
        list.get(0);
        if (list.size() > 3) {
            throw new IllegalArgumentException("哈哈");
        }
    }

    private static void stringTest() {
        //==和equals()方法,看equals()方法内部实现去理解
        char[] chars = {'a', 'b', 'c'};
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        String d = new String("abc");
        String e = new String(chars);
        System.out.println("a.equals(b)=" + a.equals(b) + "\na==b=" + (a == b) +
                "\na.equals(c)=" + a.equals(c) + "\na==c=" + (a == c) +
                "\nc.equals(d)=" + c.equals(d) + "\nc==d=" + (c == d) +
                "\nd.equals(e)=" + d.equals(e) + "\nd==e=" + (d == e)
        );

    }

    private static void arrayTest() {
        int[] a = new int[4];
        for (int i = 1; i < 5; i++) {
            a[i - 1] = i;
        }
        int[] b = {1, 2, 3, 4};
        int[] c = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
    }

    private static void objectTest() {
        Student student = new Student();
        student.name = "周杰伦";
        student.study();
        student.sayHello("刘德华");

        Student student2 = new Student();
        student2.name = "刘德华";
        student.sayHello(student2);
    }

    private static void mathTest() {
        double d = 63.5f / 10;
        float f = 6.35f;
        System.out.println(d + "-----" + f + "---" + (d == f));
        BigInteger a = new BigInteger("465746454967464");
        System.out.println("Hello World!" + a);
        BigDecimal afasg = new BigDecimal("4444444444444444444488888888888888888888888888888888855555555555555555555555555555555555666.888999");
        System.out.println("asgjagja" + afasg);
        System.out.println("fuuuuuuuy" + binaryInt);
        System.out.println("fuck--->" + fuck);
        System.out.println("fuck1--->" + fuck1);

    }

    public static class Student {
        private int age;
        private String name;
        private String gender;
        private int id;
        private int weight;

        public void study() {
            System.out.println(name + "在学习!");
        }

        public void sayHello(String name) {
            System.out.println(this.name + "对" + name + "说哈喽");
        }

        public void sayHello(Student student) {
            System.out.println(this.name + "对" + student.name + "说哈喽");
        }
    }
}
