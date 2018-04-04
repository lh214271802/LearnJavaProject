package cn.lh.learnproject.design_model.iterator_model;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式(游标cursor模式)
 * 存储与遍历数据
 */

public class IteratorDemo {

    public static void main(String[] args) {
        ConcreteMyAggregate aggregate = new ConcreteMyAggregate();
        aggregate.addObject("哈哈");
        aggregate.addObject("goajpogj");
        aggregate.addObject(15646);
        aggregate.addObject(1.245);
        aggregate.addObject(0x2FAA);
        aggregate.addObject("海东鞍山");
        aggregate.addObject(9874);
        MyIterator iterator = aggregate.iterator();
        iterator.first();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

interface MyIterator {
    //游标指向第一个元素
    void first();

    //游标指向下一个元素
    Object next();

    //是否存在下一个元素
    boolean hasNext();

    boolean isFirst();

    boolean isLast();

    //当前游标指向的对象
    Object getCurrentObj();
}

class ConcreteMyAggregate {
    private List<Object> list = new ArrayList<>();


    public void addObject(Object obj) {
        list.add(obj);
    }

    public void remove(Object obj) {
        list.remove(obj);
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }


    public MyIterator iterator() {
        return new ConcreteIterator();
    }

    //使用内部类定义迭代器
    private class ConcreteIterator implements MyIterator {

        //游标，记录遍历的位置
        private int cursor;

        @Override
        public void first() {
            cursor = 0;
        }

        @Override
        public Object next() {
            return hasNext() ? list.get(++cursor) : null;
        }

        @Override
        public boolean hasNext() {
            return cursor < list.size() - 1;
        }

        @Override
        public boolean isFirst() {
            return cursor == 0;
        }

        @Override
        public boolean isLast() {
            return cursor == list.size() - 1;
        }

        @Override
        public Object getCurrentObj() {
            return list.get(cursor);
        }
    }

}