package cn.lh.learnproject.design_model.builder;

/**
 * 建造者模式，通常会和工厂模式配合使用
 */
public class BuilderDemo {
    public static void main(String[] args) {
        AirShipBuilder builder = new MyShipBuilder();
        AirShip airShip = new MyAirShipDirector(builder).createAirShip();
    }

}

class MyAirShipDirector implements AirShipDirector {

    private AirShipBuilder builder;

    public MyAirShipDirector(AirShipBuilder builder) {
        this.builder = builder;
    }

    @Override
    public AirShip createAirShip() {
        return new AirShip(builder.buildOrbitalModule(), builder.buildEngine(), builder.buildEascapeTower());
    }
}


class MyShipBuilder implements AirShipBuilder {
    @Override
    public OrbitalModule buildOrbitalModule() {
        System.out.println("新建我的轨道舱");
        return new OrbitalModule("我的轨道舱");
    }

    @Override
    public Engine buildEngine() {
        System.out.println("新建我的引擎");
        return new Engine("我的引擎");
    }

    @Override
    public EascapeTower buildEascapeTower() {
        System.out.println("新建我的逃逸塔");
        return new EascapeTower("我的逃逸塔");
    }
}


interface AirShipBuilder {//建造者

    OrbitalModule buildOrbitalModule();

    Engine buildEngine();

    EascapeTower buildEascapeTower();
}

interface AirShipDirector {

    //组装飞船
    AirShip createAirShip();
}

class AirShip {//飞船

    public OrbitalModule orbitalModule;//轨道舱
    public Engine engine;//发动机
    public EascapeTower escapeTower;//逃逸塔

    public AirShip(OrbitalModule orbitalModule, Engine engine, EascapeTower escapeTower) {
        this.orbitalModule = orbitalModule;
        this.engine = engine;
        this.escapeTower = escapeTower;
    }
}

class OrbitalModule {

    public String name;

    public OrbitalModule(String name) {
        this.name = name;
    }
}

class Engine {

    public String name;

    public Engine(String name) {
        this.name = name;
    }
}

class EascapeTower {

    public String name;

    public EascapeTower(String name) {
        this.name = name;
    }
}
