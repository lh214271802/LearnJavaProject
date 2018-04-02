package cn.lh.learnproject.design_model.factory_model;

/**
 * Created by liaohui on 2018/4/2.
 * <p>
 * 尽量遵守OCP开闭原则
 */

public class SimpleFactory {

    public static void main(String[] args) {
        Car c1 = CarFactory1.createCar("奥迪");
        Car c2 = CarFactory1.createCar("比亚迪");

        c1.run();
        c2.run();
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

//STEP4  简单工厂模式(静态工厂模式)
class CarFactory1 {


    public static Car createCar(String type) {
        if ("奥迪".equals(type)) {
            return new Audi();
        } else if ("比亚迪".equals(type)) {
            return new Byd();
        } else {
            return null;
        }
    }

}