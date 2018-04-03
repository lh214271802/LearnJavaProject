package cn.lh.learnproject.design_model.decorator;

/**
 * 装饰模式
 * BufferedInputStream以及BufferedOutputStream就是典型的装饰者模式
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.move();
        System.out.println("=====================================");
        SuperCar s1 = new FlyCar(car);
        s1.move();
        System.out.println("=====================================");
        SuperCar s2 = new WaterCar(s1);
        s2.move();
    }
}

//抽象组件
interface ICar {
    void move();
}

//被装饰对象
class Car implements ICar {
    @Override
    public void move() {
        System.out.println("陆地上跑");
    }
}

//装饰器
class SuperCar implements ICar {

    private ICar car;

    public SuperCar(ICar car) {
        this.car = car;
    }

    @Override
    public void move() {
        car.move();
    }
}

//具体装饰角色
class FlyCar extends SuperCar {

    public FlyCar(ICar car) {
        super(car);
    }

    public void fly() {
        System.out.println("天上飞");
    }

    @Override
    public void move() {
        super.move();
        fly();
    }
}

//具体装饰角色
class WaterCar extends SuperCar {

    public WaterCar(ICar car) {
        super(car);
    }

    public void swim() {
        System.out.println("水里游");
    }

    @Override
    public void move() {
        super.move();
        swim();
    }
}