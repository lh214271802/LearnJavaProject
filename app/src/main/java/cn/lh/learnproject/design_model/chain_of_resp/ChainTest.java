package cn.lh.learnproject.design_model.chain_of_resp;

/**
 * 责任链模式，层层递进，自己不能处理的交给上级处理
 * try{}catch{}的异常捕获机制就是典型的责任链模式
 */
public class ChainTest {
    public static void main(String[] args) {

        Leader a = new Director("张三");
        Leader b = new Manager("李四");
        Leader c = new GeneralManager("王五");
        a.nextLeader = b;
        b.nextLeader = c;

        LeaveRequest leaveRequest = new LeaveRequest("TOM", 9, "病假");
        a.handleRequest(leaveRequest);
    }
}


//请假类
class LeaveRequest {
    //员工名
    public String empName;
    //请假天数
    public int leaveDays;
    //请假理由
    public String reason;

    public LeaveRequest(String empName, int leaveDays, String reason) {
        this.empName = empName;
        this.leaveDays = leaveDays;
        this.reason = reason;
    }

}


abstract class Leader {
    public String name;
    public LeaveRequest leaveRequest;
    public Leader nextLeader;

    public Leader(String name, Leader nextLeader) {
        this.name = name;
        this.nextLeader = nextLeader;
    }

    public Leader(String name) {
        this.name = name;
    }

    public abstract void handleRequest(LeaveRequest request);
}

class Director extends Leader {

    public Director(String name, Leader nextLeader) {
        super(name, nextLeader);
    }

    public Director(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        this.leaveRequest = request;
        if (leaveRequest.leaveDays <= 3) {
            System.out.println("主任" + name + "通过了" + leaveRequest.empName + "的" + leaveRequest.leaveDays + "天请假请求");
        } else {
            nextLeader.handleRequest(request);
        }
    }
}

class Manager extends Leader {
    public Manager(String name, Leader nextLeader) {
        super(name, nextLeader);
    }

    public Manager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        this.leaveRequest = request;
        if (leaveRequest.leaveDays <= 10) {
            System.out.println("经理" + name + "通过了" + leaveRequest.empName + "的" + leaveRequest.leaveDays + "天请假请求");
        } else {
            nextLeader.handleRequest(request);
        }
    }
}

class GeneralManager extends Leader {
    public GeneralManager(String name, Leader nextLeader) {
        super(name, nextLeader);
    }

    public GeneralManager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        this.leaveRequest = request;
        if (leaveRequest.leaveDays <= 30) {
            System.out.println("总经理" + name + "通过了" + leaveRequest.empName + "的" + leaveRequest.leaveDays + "天请假请求");
        } else {
            nextLeader.handleRequest(request);
        }
    }
}