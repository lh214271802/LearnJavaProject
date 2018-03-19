package cn.lh.learnproject.synread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liaohui on 2018/3/19.
 */

public class SynreadTest {
    public static void main(String[] args) {
        //线程安全同步
        threadTest();
        //制作不可变容器
        testUnmodifiable();
    }

    private static void testUnmodifiable() {
        Map<String, String> map = new HashMap<>();
        map.put("11", "aaa");
        Map<String, String> unmodifiableMap = Collections.unmodifiableMap(map);
        try {
            unmodifiableMap.put("22", "BBB");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("容器元素个数:" + unmodifiableMap.size());
        }
    }

    private static void threadTest() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        //list可以同步
        List<String> synList = Collections.synchronizedList(list);
        System.out.println("线程安全的list制作完毕");
    }
}
