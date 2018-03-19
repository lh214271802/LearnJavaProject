package cn.lh.learnproject.sort;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by liaohui on 2018/3/16.
 */

public class SortMain {

    public static void main(String[] args) {
        int[] arr = new int[]{124, 325, 12, 526, 23,
                36, 73, 732, 343, 1, 38,
                56, 183, 27427, 373, 7842, 3444,
                56, 157583, 27427, 3473, 742, 3344,
                5, 5, 3473, 742, 3344,
                5, 5, 75, 677, 754, 7349,
                4, 18243, 3242627, 4357, 742, 33474,
                5456, 183, 97, 373, 742, 3844,
                4243, 876, 85, 75, 754, 7849,
                36, 73, 732, 343, 1, 38,
                56, 183, 27427, 373, 7842, 3444,
                56, 157583, 27427, 3473, 742, 3344,
                4, 18243, 3242627, 4357, 742, 33474,
                5456, 183, 97, 373, 742, 3844,
                4243, 876, 85, 75, 754, 7849,
                36, 73, 732, 343, 1, 38,
                56, 183, 27427, 373, 7842, 3444,
                56, 157583, 27427, 3473, 3473, 742, 3344,
                5, 5, 75, 677, 754, 7349,
                5, 5, 75, 677, 754, 7349,
                4, 18243, 3242627, 4357, 742, 33474,
                5456, 183, 97, 373, 742, 3844,
                4243, 876, 85, 75, 754, 7849,
                36, 73, 732, 343, 1, 38,
                56, 183, 27427, 373, 7842, 3444,
                56, 157583, 27427, 3473, 742, 3344,
                4, 18243, 3242627, 4357, 742, 33474,
                5456, 183, 97, 373, 742, 3844,
                4243, 876, 85, 75, 754, 7849,
                36, 73, 732, 343, 1, 38,
                56, 183, 27427, 373, 7842, 3444,
                56, 157583, 27427, 3473, 3473, 742, 3344,
                5, 5, 75, 677, 754, 7349,
                5, 5, 75, 677, 754, 7349,
                4, 18243, 3242627, 4357, 742, 33474,
                5456, 183, 97, 373, 742, 3844,
                4243, 876, 85, 75, 754, 7849,
                36, 73, 732, 343, 1, 38,
                56, 183, 27427, 373, 7842, 3444,
                56, 157583, 27427, 3473, 742, 3344,
                4, 18243, 3242627, 4357, 742, 33474,
                5456, 183, 97, 373, 742, 3844,
                4243, 876, 85, 75, 754, 7849,
                36, 73, 732, 343, 1, 38,
                56, 183, 27427, 373, 7842, 3444,
                56, 157583, 27427, 3473, 3473, 742, 3344,
                5, 5, 75, 677, 754, 7349,
                5, 5, 75, 677, 754, 7349,
                4, 18243, 3242627, 4357, 742, 33474,
                5456, 183, 97, 373, 742, 3844,
                4243, 876, 85, 75, 754, 7849,
                36, 73, 732, 343, 1, 38,
                56, 183, 27427, 373, 7842, 3444,
                56, 157583, 27427, 3473, 742, 3344,
                4, 18243, 3242627, 4357, 742, 33474,
                5456, 183, 97, 373, 742, 3844,
                4243, 876, 85, 75, 754, 7849,
                36, 73, 732, 343, 1, 38,
                56, 183, 27427, 373, 7842, 3444,
                56, 157583, 27427, 3473, 3473, 742, 3344,
                5, 5, 75, 677, 754, 7349,
                4, 18243, 3242627, 4357, 742, 33474,
                5456, 183, 97, 373, 742, 3844,
                4243, 876, 85, 75, 754, 7849, 7849};
        bubbleSort(arr);
//        System.out.println("time-start->" + System.currentTimeMillis());
//        Arrays.sort(arr);
//        System.out.println("最后：" + Arrays.toString(arr));
//        System.out.println("time-end->" + System.currentTimeMillis());
        SortAge[] sortAges = {new SortAge(5),
                new SortAge(4),
                new SortAge(33),
                new SortAge(67),
                new SortAge(43)};
        Arrays.sort(sortAges);
        Arrays.asList(sortAges).<SortAge>toArray(new SortAge[sortAges.length]);
        System.out.println(Arrays.toString(sortAges));

//        Collections.sort(Arrays.asList(sortAges));


        //自带排序功能，但是泛型类必须是实现了Comparable接口的类
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
    }

    /**
     * 冒泡排序，考虑了有序的可能性，优化性能
     */
    private static void bubbleSort(int[] arr) {
        System.out.println("time-start->" + System.currentTimeMillis());
        for (int i = 0; i < arr.length - 1; i++) {
            boolean sorted = true;//假设已经排好序了
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    sorted = false;
                }
            }
//            System.out.println("第" + (i + 1) + "次：" + Arrays.toString(arr));
            if (sorted) {
                break;
            }
        }
        System.out.println("最后：" + Arrays.toString(arr));
        System.out.println("time-end->" + System.currentTimeMillis());
    }
}

class SortAge implements Comparable<SortAge>, Comparator<SortAge> {
    int age;

    public SortAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(@NonNull SortAge o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "SortAge{" +
                "age=" + age +
                '}';
    }

    @Override
    public int compare(SortAge o1, SortAge o2) {
        return o1.age - o2.age;
    }
}