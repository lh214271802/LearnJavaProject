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
        System.out.println("==========================下面的是抽象工厂模式==================================");
        //三  抽象工厂模式
        IAbstractCarFactory factory = new LuxuryCarFactory();
        Engine engine = factory.createEngine();
        engine.run();
        engine.start();

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

//三、抽象工厂模式，在有多个业务品种，业务分类时，通过抽象工厂模式产生需要的对象是一种非常好的解决方式
interface Engine {
    void run();

    void start();
}

class LuxuryEngine implements Engine {
    @Override
    public void run() {
        System.out.println("转的快");
    }

    @Override
    public void start() {
        System.out.println("启动快！可以自动启停！");
    }
}

class LowEngine implements Engine {

    @Override
    public void run() {
        System.out.println("转的慢~");
    }

    @Override
    public void start() {
        System.out.println("启动慢~");
    }
}

interface Seat {
    void massage();
}

class LuxurySeat implements Seat {

    @Override
    public void massage() {
        System.out.println("可以自动按摩");
    }
}

class LowSeat implements Seat {

    @Override
    public void massage() {
        System.out.println("不可以自动按摩");
    }
}

interface Tyre {
    void revolve();
}

class LuxuryTyre implements Tyre {
    @Override
    public void revolve() {
        System.out.println("旋转不磨损");
    }
}

class LowTyre implements Tyre {
    @Override
    public void revolve() {
        System.out.println("旋转磨损快");
    }
}

interface IAbstractCarFactory {
    Engine createEngine();

    Seat createSeat();

    Tyre createTyre();
}

class LuxuryCarFactory implements IAbstractCarFactory {

    @Override
    public Engine createEngine() {
        return new LuxuryEngine();
    }

    @Override
    public Seat createSeat() {
        return new LuxurySeat();
    }

    @Override
    public Tyre createTyre() {
        return new LuxuryTyre();
    }
}

class LowCarFactory implements IAbstractCarFactory {

    @Override
    public Engine createEngine() {
        return new LowEngine();
    }

    @Override
    public Seat createSeat() {
        return new LowSeat();
    }

    @Override
    public Tyre createTyre() {
        return new LowTyre();
    }
}