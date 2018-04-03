package cn.lh.learnproject.design_model.proxy_model;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liaohui on 2018/3/20.
 * 静态代理模式---->Thread和Runnable就是典型的静态代理模式
 */

public class StaticProxyDemo {
    public static void main(String[] args) {
        //创建真实角色
        Marry you = new You();
        //创建代理角色
        Marry company = new WeddingCompany(you);
        company.marry();

        ////////////动态代理模式///////////////////

        System.out.println("===========下面是动态代理模式===================");
        MarryHandler handler = new MarryHandler(you);
        Marry marry = (Marry) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Marry.class}, handler);
        marry.marry();
    }
}

interface Marry {
    public abstract void marry();
}

//真实角色
class You implements Marry {
    @Override
    public void marry() {
        System.out.println("you and chang e 结婚啦!");
    }
}

//代理角色,持有真实角色的引用，进而对真实角色的功能进行拓展补充
class WeddingCompany implements Marry {
    private Marry you;

    public WeddingCompany() {
    }

    public WeddingCompany(Marry you) {
        this.you = you;
    }

    private void before() {
        System.out.println("布置猪窝");
    }

    private void after() {
        System.out.println("闹洞房");
    }

    @Override
    public void marry() {
        before();
        you.marry();
        after();
    }

}

//////////////////////////////动态代理模式
class MarryHandler implements InvocationHandler {
    Marry marry;

    public MarryHandler(Marry marry) {
        this.marry = marry;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        System.out.println("真正的方法执行前");
        if (method.getName().equals("marry")) {
            obj = method.invoke(marry, args);
        }
        System.out.println("真正的方法执行后");
        return obj;
    }
}
