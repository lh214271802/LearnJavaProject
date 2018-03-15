package cn.lh.learnproject.learn;

public class HttpServlet {
    public void service() {
        System.out.println("service()");
        doGEet();
    }

    public void doGEet() {
        System.out.println("doGet()");
    }

    public void doPost() {
    }
}
