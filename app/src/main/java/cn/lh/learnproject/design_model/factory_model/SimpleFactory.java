package cn.lh.learnproject.design_model.factory_model;

/**
 * Created by liaohui on 2018/4/2.
 * <p>
 * 尽量遵守OCP开闭原则
 */

public class SimpleFactory {

    public static void main(String[] args) {
        //一  简单工厂模式(如果添加新的车型，需要修改代码，不符合开闭原则)
        Car c1 = CarFactory1.createCar("奥迪");
        Car c2 = CarFactory1.createCar("比亚迪");

        c1.run();
        c2.run();
        System.out.println("==========================下面的占优，但是最常用的还是简单工厂模式==================================");
        //二  工厂方法模式(符合开闭原则)
        Car c11 = new AudiFactory().createCar();
        Car c22 = new BydFactory().createCar();
        Car c3 = new BenzFactory().createCar();

        c11.run();
        c22.run();
        c3.run();

    }
}

//STEP1
interface Car {
    void run();
}

//STEP2
class Audi implements Car {

    @Override
    public void run() {
        System.out.println("奥迪在跑");
    }
}

//STEP3
class Byd implements Car {

    @Override
    public void run() {
        System.out.println("比亚迪在跑");
    }
}

class Benz implements Car {

    @Override
    public void run() {
        System.out.println("奔驰在跑");
    }
}

//STEP4  一、简单工厂模式(静态工厂模式)
class CarFactory1 {


    public static Car createCar(String type) {
        if ("奥迪".equals(type)) {
            return new Audi();
        } else if ("比亚迪".equals(type)) {
            return new Byd();
        } /*else if ("奔驰".equals(type)) {
            return new Byd();
        }*/ else {
            return null;
        }
    }

}

//二、工厂方法模式
interface ICarFactory {
    Car createCar();
}

class AudiFactory implements ICarFactory {

    @Override
    public Car createCar() {
        return new Audi();
    }
}

class BydFactory implements ICarFactory {

    @Override
    public Car createCar() {
        return new Byd();
    }
}

class BenzFactory implements ICarFactory {

    @Override
    public Car createCar() {
        return new Benz();
    }
}
