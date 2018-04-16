package cn.lh.learnproject;

/**
 * Created by liaohui on 2018/4/9.
 * <p>
 * 回文串：正向和翻转后的字符串相等
 * 将一个字符串中的字符移除0个或多个生成回文串的方案个数
 */

public class Test {
    private Test() {
    }

    private static class Holder {
        private static final Test test = new Test();
    }


    public static void main(String[] args) {

        Test test = Holder.test;
        long start = System.currentTimeMillis();
        System.out.println(getNum("asaddfgagssdsag"));
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start));

        System.out.println("==============================================");
        String[] aa = {"adasg",
                "bgsa",
                "cdgsg",
                "ddsgdf",
                "eds",
                "fsdf",
                "hdf",
                "jder"};
        System.out.println(binarySearch(aa, 0, aa.length, "hdf"));
    }

    // 使用递归实现的二分查找
    private static <T extends Comparable<T>> int binarySearch(T[] x, int low, int high, T key) {
        if (low <= high) {
            int mid = (high + low) >>> 1;
            if (key.compareTo(x[mid]) == 0) {
                return mid;
            } else if (key.compareTo(x[mid]) < 0) {
                return binarySearch(x, low, mid - 1, key);
            } else {
                return binarySearch(x, mid + 1, high, key);
            }
        }
        return -1;
    }

    private static int getNum(String oldStr) {
        int num = 0;

        StringBuilder defStr = new StringBuilder(oldStr);
        StringBuilder reverStr = new StringBuilder(oldStr).reverse();
        //不移除字符的情况
        if (defStr.toString().equals(reverStr.toString())) {
            num++;
        }
        int length = oldStr.length();

        //至少移除一个字符的情况
        //deleteNum移除字符的个数
        for (int deleteNum = 1; deleteNum < length; deleteNum++) {
            //移除的时候只能按顺序移除，升序移除（如长度为10的字符串移除2个字符，只能是移除第n和m个字符，m必须大于n），排除重复的移除方案
            int nowDeleteNum = deleteNum;
            //移除字符的方案，至少可以从startIndex的位置开始移除，而字符串中最多可以移除length-1个字符，
            //当需要移除nowDeleteNum个字符的时候，最多能移除到此字符串末尾连续nowDeleteNum个字符，所以startIndex必须小于等于length-nowDeleteNum（因为是升序移除）
            int endIndex = length - nowDeleteNum;
            for (int startIndex = 0; startIndex <= endIndex; startIndex++) {
                num += deleteChar(startIndex, endIndex, nowDeleteNum, new StringBuilder(oldStr), new StringBuilder(oldStr).reverse());
            }
        }
        return num;
    }

    private static int deleteChar(int startIndex, int endIndex, int deleteNum, StringBuilder defStr, StringBuilder reverStr) {
        int num = 0;
        while (deleteNum > 0) {
            defStr.deleteCharAt(startIndex);
            reverStr.deleteCharAt(reverStr.length() - startIndex - 1);
            deleteNum--;
            for (int mStart = startIndex; mStart <= endIndex - 1 && mStart >= startIndex && deleteNum > 0; mStart++) {
                num += deleteChar(mStart, endIndex - 1, deleteNum, new StringBuilder(defStr.toString()), new StringBuilder(reverStr.toString()));
            }
            if (deleteNum == 0) {
                if (defStr.toString().equals(reverStr.toString())) {
                    num++;
                }
            }
        }

        return num;
    }


}
