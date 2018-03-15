package cn.lh.learnproject.learn2;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by liaohui on 2018/3/15.
 */

public class Main {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("1", "l");
        map.put("2", "i");
        map.put("3", "a");
        map.put("4", "o");
        map.put("5", "h");
        map.put("6", "u");
        map.put("7", "i");
        Iterator iterator = map.entrySet().iterator();
        Iterator iterator1 = map.keySet().iterator();
        Iterator iterator2 = map.values().iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + "\t");
        }
        System.out.println();
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + "\t");
        }
        System.out.println();
    }
}
