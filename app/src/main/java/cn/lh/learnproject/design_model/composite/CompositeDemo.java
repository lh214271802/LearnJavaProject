package cn.lh.learnproject.design_model.composite;

/**
 * 组合模式
 */
public class CompositeDemo {

    public static void main(String[] args) {

    }
}

interface Component {
    void operation();
}

//叶子组件
interface leaf extends Component {
}

//容器组件
interface Composite extends Component {
    void add(Component c);

    void remove(Component c);

    Component getChild(int index);
}