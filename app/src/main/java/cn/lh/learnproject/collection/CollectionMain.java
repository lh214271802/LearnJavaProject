package cn.lh.learnproject.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liaohui on 2018/3/19.
 */

public class CollectionMain {

    public static void main(String[] args) {
        testCollection();
        //模拟斗地主洗牌发牌
        testShuffle();
    }

    private static void testShuffle() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            list.add(i);
        }
        //开始洗牌
        Collections.shuffle(list);
        //依次发牌
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> last = new ArrayList<>();
        for (int i = 0; i < 51; i += 3) {
            list1.add(list.get(i));
            list2.add(list.get(i + 1));
            list3.add(list.get(i + 2));
        }
        last.add(list.get(51));
        last.add(list.get(52));
        last.add(list.get(53));
        System.out.println("第一个人" + list1);
        System.out.println("第二个人" + list2);
        System.out.println("第三个人" + list3);
        System.out.println("底牌为" + last);
    }

    private static void testCollection() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("翻转前" + list);
        Collections.reverse(list);
        System.out.println("翻转后" + list);
    }
}
