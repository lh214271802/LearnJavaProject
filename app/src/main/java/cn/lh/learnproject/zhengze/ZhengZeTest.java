package cn.lh.learnproject.zhengze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 */
public class ZhengZeTest {

    public static void main(String[] args) {


        // \n换行符  \t制表符  \\就是\本身
        // 下面的匹配方式大写字符代表相反的匹配方式
        // \d任意一个数字都匹配  \w任意一个字母数字或下划线
        // \s空格、制表符、换行符等空白字符中的任意一个
        // 小数点.可以匹配任意一个字符，如果要匹配包括\n在内的所有字符一般用[\s\S]
        // {n}表达式重复n次，{m,n}至少重复m次最多重复n次，{m,}至少重复m次
        // ?表示出现0次或者1次，同{0,1}  +至少出现1次，同{1,}  *不出现或者出现任意次，同{0,}
        // ^与字符开始的位置匹配，$与字符结束的位置匹配，\b匹配一个单词边界
        Pattern p = Pattern.compile("\\w.");
        Matcher matcher = p.matcher("ahgohadd&&&as_jagsd&&ddsgd&&&oidhi");
        boolean matches = matcher.matches();
        System.out.println(matches);
        boolean b;
        while (b = matcher.find()) {
            System.out.println(b);
            System.out.println(matcher.group());
        }

        String replaceAll = matcher.replaceAll("@");
        System.out.println(replaceAll);
    }
}
