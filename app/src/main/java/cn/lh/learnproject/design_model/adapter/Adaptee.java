package cn.lh.learnproject.design_model.adapter;

/**
 * 被适配的类
 */
public class Adaptee {

    public void request() {
        System.out.println("可以完成客户请求的需要的功能！");
    }
}

class Test {


    public static void main(String[] args) {
        Client c = new Client();
        Adaptee a = new Adaptee();
        Target t = new Adapter(a);
        c.test(t);
    }
}

interface Target {
    void handleReq();
}

//客户端
class Client {

    public void test(Target t) {
        t.handleReq();
    }
}

//适配器本身
class Adapter implements Target {

    private Adaptee adaptee;

    @Override
    public void handleReq() {
        adaptee.request();
    }

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
}