package cn.lh.learnproject;

/**
 * Created by liaohui on 2018/4/9.
 * <p>
 * 回文串：正向和翻转后的字符串相等
 * 将一个字符串中的字符移除0个或多个生成回文串的方案个数
 */

public class Test {

    public static void main(String[] args) {

    }


    private static int getNum(String str) {
        int num = 0;

        StringBuilder defStr = new StringBuilder(str);
        StringBuilder reverStr = new StringBuilder(str).reverse();
        //不移除字符的情况
        if (defStr.toString().equals(reverStr.toString())) {
            num++;
        }
        int length = str.length();

        //至少移除一个字符的情况
        //deleteNum移除字符的个数
        for (int deleteNum = 1; deleteNum < length; deleteNum++) {
            //移除的时候只能按顺序移除，升序移除（如长度为10的字符串移除2个字符，只能是移除第n和m个字符，m必须大于n），排除重复的移除方案
            int a = deleteNum;
            //移除字符的方案，至少可以从j的位置开始移除，而字符串中最多可以移除length-1个字符，
            //当需要移除a个字符的时候，最多能移除到此字符串末尾连续a个字符，所以j必须小于length-a（因为是升序移除）
            for (int j = 0; j <= length - a; j++) {
                while (a > 0) {
                    int nowLength = defStr.length();
                    for (int m = j; m < nowLength; m++) {
                        //怎么组合移除？
                        defStr.deleteCharAt(m);
                        reverStr.deleteCharAt(nowLength - m);
                        nowLength = defStr.length();


                        if (defStr.toString().equals(reverStr.toString())) {
                            num++;
                        }
                        a--;
                        if (a == 0) {
                            defStr = new StringBuilder(str);
                            reverStr = new StringBuilder(str).reverse();
                        }
                    }
                }

            }

        }

        return num;
    }


}
