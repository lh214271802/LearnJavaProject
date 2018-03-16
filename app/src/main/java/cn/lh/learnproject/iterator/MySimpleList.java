package cn.lh.learnproject.iterator;

import java.util.Arrays;

/**
 * Created by liaohui on 2018/3/16.
 */

public class MySimpleList {
    public String[] elem = {"a", "b", "c", "d", "e", "f", "g"};
    private int size;

    public MySimpleList() {
        size = elem.length;
    }

    public int getSize() {
        return size;
    }

    //计数器，相当于C里面的指针游标
    private int course = -1;

    public boolean hasNext() {
        return course + 1 < size;
    }

    public String next() {
        course++;
        return elem[course];
    }

    public void remove() {
        System.arraycopy(elem, course + 1, elem, course, size - course - 1);
        size--;
        course--;
    }
}

class Test {

    public static void main(String[] args) {
        MySimpleList mySimpleList = new MySimpleList();
        if (mySimpleList.hasNext()) {
            System.out.println(mySimpleList.next());
        }
        if (mySimpleList.hasNext()) {
            System.out.println(mySimpleList.next());
        }
        mySimpleList.remove();
        if (mySimpleList.hasNext()) {
            System.out.println(mySimpleList.next());
        }
        if (mySimpleList.hasNext()) {
            System.out.println(mySimpleList.next());
        }
        mySimpleList.remove();
        mySimpleList.remove();
        if (mySimpleList.hasNext()) {
            System.out.println(mySimpleList.next());
        }
        if (mySimpleList.hasNext()) {
            System.out.println(mySimpleList.next());
        }

        System.out.println(Arrays.toString(mySimpleList.elem));
        System.out.println(mySimpleList.getSize());
    }
}