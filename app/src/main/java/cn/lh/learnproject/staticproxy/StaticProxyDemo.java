package cn.lh.learnproject.staticproxy;


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
new String("fasioos");
        company.marry();
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
