package cn.lh.learnproject.design_model.bridge;

/**
 * 桥接模式
 */
public class BridgeDemo {
    public static void main(String[] args) {

        Computer2 c = new Laptop2(new Lenovo());
        c.sale();
        System.out.println("=================================");
        Computer2 c2 = new Pad2(new Lenovo());
        c2.sale();
    }
}

interface Computer {
    void sale();
}

class Desktop implements Computer {
    @Override
    public void sale() {
        System.out.println("销售台式机");
    }
}

class Laptop implements Computer {
    @Override
    public void sale() {
        System.out.println("销售笔记本");
    }
}
//////////////////两种影响维度：品牌和类型，下面这种写法耦合性较强///////////////////


class Pad implements Computer {
    @Override
    public void sale() {
        System.out.println("销售平板电脑");
    }
}

class LenovoDesktop extends Desktop {
    @Override
    public void sale() {
        System.out.println("销售联想牌台式机");
    }
}

class LenovoLaptop extends Laptop {
    @Override
    public void sale() {
        System.out.println("销售联想笔记本");
    }
}

class LenovoPad extends Pad {
    @Override
    public void sale() {
        System.out.println("销售联想平板电脑");
    }
}


class shenZhouDesktop extends Desktop {
    @Override
    public void sale() {
        System.out.println("销售神州牌台式机");
    }
}

class shenZhouLaptop extends Laptop {
    @Override
    public void sale() {
        System.out.println("销售神州笔记本");
    }
}

class shenZhouPad extends Pad {
    @Override
    public void sale() {
        System.out.println("销售神州平板电脑");
    }
}

class dellDesktop extends Desktop {
    @Override
    public void sale() {
        System.out.println("销售戴尔牌台式机");
    }
}

class dellLaptop extends Laptop {
    @Override
    public void sale() {
        System.out.println("销售戴尔笔记本");
    }
}

class dellPad extends Pad {
    @Override
    public void sale() {
        System.out.println("销售戴尔平板电脑");
    }
}
//////////////////把品牌和类型两个维度分开///////////////////

interface Brand {//品牌

    void sale();
}

class Lenovo implements Brand {

    @Override
    public void sale() {
        System.out.println("销售联想电脑");
    }
}

class Dell implements Brand {

    @Override
    public void sale() {
        System.out.println("销售戴尔电脑");
    }
}

class ShenZhouo implements Brand {

    @Override
    public void sale() {
        System.out.println("销售神州电脑");
    }
}

class Computer2 {
    protected Brand brand;

    public Computer2(Brand brand) {
        this.brand = brand;
    }

    public void sale() {
        brand.sale();
    }
}

class Desktop2 extends Computer2 {

    public Desktop2(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售台式机");
    }
}

class Laptop2 extends Computer2 {

    public Laptop2(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售笔记本");
    }
}

class Pad2 extends Computer2 {

    public Pad2(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售平板电脑");
    }
}