package cn.lh.learnproject.design_model.fly_weight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式,享元模式以共享的方式高效地支持大量细粒度对象的重用
 * 通常会和工厂模式搭配起来用
 */
public class FlyWeightDemo {

    public static void main(String[] args) {
        ChessFlyWeight chess1 = ChessFlyWeightFactory.getChess("黑色");
        ChessFlyWeight chess2 = ChessFlyWeightFactory.getChess("黑色");
        System.out.println(chess1);
        System.out.println(chess2);

        System.out.println("============增加外部处理============");
        chess1.display(new Coordinate(10, 10));
        chess1.display(new Coordinate(20, 20));

    }
}


/**
 * 围棋，享元类
 */
interface ChessFlyWeight {
    void setColor(String color);

    String getColor();

    void display(Coordinate c);
}

/**
 * 外部状态
 */
class Coordinate {
    public int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class ConcreteChess implements ChessFlyWeight {

    private String color;

    public ConcreteChess(String color) {
        this.color = color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void display(Coordinate c) {
        System.out.println("棋子颜色-->" + color);
        System.out.println("棋子位置-->x:" + c.x + "--->y:" + c.y);
    }
}

/**
 * 享元工厂类
 */

class ChessFlyWeightFactory {
    //享元池
    private static Map<String, ChessFlyWeight> map = new HashMap<>();


    public static ChessFlyWeight getChess(String color) {
        if (map.get(color) != null) {
            return map.get(color);
        } else {
            ChessFlyWeight cfw = new ConcreteChess(color);
            map.put(color, cfw);
            return cfw;
        }
    }

}