package cn.lh.learnproject.iotest;

import java.io.Serializable;

/**
 * Created by liaohui on 2018/3/20.
 */

public class Employee implements Serializable {
    //加上transient，不需要序列化
    private transient int age;
    //其他的就需要序列化
    private double salary;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
