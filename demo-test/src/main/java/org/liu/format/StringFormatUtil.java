package org.liu.format;

public class StringFormatUtil {

    public static void main(String[] args) {
        format();
    }

    private static void format() {
        String s = "我是咩咩咩咩%s,我%d岁啦";
        String format = String.format(s, "a", 15);
        System.out.println(format);
    }

    public static void test() {
        int youNumber = 1;
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String str = String.format("%04d", youNumber);

        System.out.println(str); // 0001
    }

}
