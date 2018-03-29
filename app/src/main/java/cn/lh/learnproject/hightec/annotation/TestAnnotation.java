package cn.lh.learnproject.hightec.annotation;

import android.annotation.TargetApi;
import android.os.Build;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import cn.lh.learnproject.MainActivity;

@MyAnnotation(name = {"嘻哈", "点击ski"}, age = 18, id = 22)
@SuppressWarnings(value = "all")
public class TestAnnotation {

    public static void main(String[] args) {
        testDeprecated();
        TestAnnotation testAnnotation = new TestAnnotation();
        testAnnotation.test();

        //Lambda表达式使用与测试
//        testAnnotation.testLambda();
//        testAnnotation.testMethodQuote();
        // 注解
//        testAnnotation.fuckAnnotation();
        //反射
        testAnnotation.fuckReflect();
    }

    @Deprecated
    public static void testDeprecated() {
        System.out.println("发个有");
    }

    public void test() {
        Annotation[] annotations = TestAnnotation.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
            Class<? extends Annotation> aClass = annotation.annotationType();
            System.out.println(aClass.toString());
        }
    }


    /**
     * 反射测试
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void fuckReflect() {
        Class<TestReflect> cls = TestReflect.class;
        cls.getFields();
        for (Method method : cls.getMethods()) {
            int modifiers = method.getModifiers();
            modifiers = modifiers & Modifier.methodModifiers();
            LogUtils.e("fuck mod---" + Modifier.toString(modifiers) + "--name-->" + method.getName());
        }
        Constructor[] constructors = cls.getConstructors();
        for (Constructor constructor : constructors) {
            LogUtils.e("fuck constructor.getName " + constructor.getName());
        }
    }

    /**
     * Java 8 方法引用的使用与测试
     * <p>
     * 方法引用通过方法的名字来指向一个方法,方法引用使用一对冒号(::),Java中支持的4种不同的方法引用
     */
    @TargetApi(Build.VERSION_CODES.N)
    private void testMethodQuote() {
        //TODO 构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);

        //TODO 静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach(Car::collide);

        //TODO 特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach(Car::repair);

        //TODO 特定对象的方法引用：它的语法是instance::method实例如下：
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

    public static class Car {
        public Car() {

        }

        @TargetApi(Build.VERSION_CODES.N)
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }
    }

    /**
     * Java 8 Lambda表达式使用与测试
     */
    private void testLambda() {
        //fixme java8 lambda 表达式
//        textView.setOnClickListener(v -> {
//
//        });

        //FIXME lambda 表达式只能引用 final 或 final 局部变量，这就是说不能在 lambda 内部修改定义在域外的变量，否则会编译错误
        final int[] params = {1};
        //TODO 小括号必须要，大括号在函数体只有一句的时候可要可不要，大于一句的必须要
        FuckJava8Method fuckJava8Method = () -> {
            params[0] = 3;
        };
        //TODO 无大括号，直接返回箭头后面的数据；括号中的类型声明可要可不要
        FuckJava8Method2 fuckJava8Method21 = (/*int*/ a,/*int*/ b) -> a + b;
        //TODO 有大括号，需要添加return语句返回数据
        FuckJava8Method2 fuckJava8Method22 = (a, b) -> {
            a = a * b;
            return a - b;
        };
        //TODO 无小括号
        FuckJava8Method3 fuckJava8Method31 = str -> ToastUtils.showShort(str);
        //TODO 有小括号，类型声明可要可不要
        FuckJava8Method3 fuckJava8Method32 = (/*String*/ str) -> ToastUtils.showShort(str);
    }

    interface FuckJava8Method {
        void fuckYou();
    }

    interface FuckJava8Method2 {

        int fuckMe(int a, int b);
    }

    interface FuckJava8Method3 {

        void fuckMe(String str);
    }


    //TODO 注解
    @MyAnnotation(str = "bitch", value = 9)
    public static void fuckAnnotation() {
        MainActivity activity = new MainActivity();
        Class cls = activity.getClass();
        try {
            Method m = cls.getMethod("fuckAnnotation");
            MyAnnotation myAnnotation = m.getAnnotation(MyAnnotation.class);
            ToastUtils.showShort("myAnnotation.str = " + myAnnotation.str() + "---myAnnotation.value = " + myAnnotation.value());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAnnotation {
        String str() default "fuck";

        int value() default 88;
    }
}
