package cn.lh.learnproject.design_model.command_model;

/**
 * 命令模式
 */
public class CommandDemo {

    public static void main(String[] args) {
        Command command = new ConcreteCommand(new Receiver());
        Invoke i = new Invoke(command);
        i.call();

    }

}

/**
 * 命令执行者
 */
class Receiver {
    public void action() {
        System.out.println("action");
    }
}

//命令
interface Command {
    /**
     * 此方法为返回结果为空的方法
     * 实际项目中可以根据需求设计多个不同的方法
     */
    void execute();
}

class ConcreteCommand implements Command {
    //命令执行者
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //命令真正执行前后，执行相关的处理
        receiver.action();
    }
}

//调用者，发起者
class Invoke {
    //也可以通过容器List<Command>容纳很多命令对象，进行处理，数据库底层的事务管理就是类似的结构
    private Command command;

    public Invoke(Command command) {
        this.command = command;
    }

    //业务方法，用于调用命令类的方法
    public void call() {
        command.execute();
    }
}