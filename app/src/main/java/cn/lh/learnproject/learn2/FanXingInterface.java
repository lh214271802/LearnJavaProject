package cn.lh.learnproject.learn2;

/**
 * Created by liaohui on 2018/3/15.
 * 1.泛型只能使用引用类型，不能使用基本类型
 * 2.泛型声明时字母不能使用在静态属性或静态方法上
 * 3.泛型也不能用在接口的变量中，只能用在接口的方法中
 * <p>
 * 泛型没有多态，只能使用 ？ extends 某某类 来达到多态的功能
 */

public interface FanXingInterface<T> {
    void test(T t);
}
