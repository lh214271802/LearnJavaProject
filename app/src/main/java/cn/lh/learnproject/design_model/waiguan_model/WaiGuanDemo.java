package cn.lh.learnproject.design_model.waiguan_model;

/**
 * 外观模式（门面模式），最常用的模式
 */
public class WaiGuanDemo {

    public static void main(String[] args) {
        new RegisterFacade().register();
    }

}

/**
 * 办理注册公司流程的门面对象
 */
class RegisterFacade {

    public void register() {
        System.out.println("我来注册吧");
        System.out.println("类1流程1");
        System.out.println("类2流程2");
        System.out.println("类3流程3");
        System.out.println("类4流程4");
        System.out.println("注册完成啦");
    }

}