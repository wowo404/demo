package org.liu.hutool;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import static org.liu.hutool.StrExtendUtil.ordinalIndexOf;

public class TestHutool {

    public static void main(String[] args) {
        System.out.println(Math.abs(-7.77));
        System.out.println(NumberUtil.isNumber("+123.564"));

        String text = "绝对值($d)*(绝对值($e)+$f)-绝对值({$a+绝对值($b+($c*$g))})";
        System.out.println(ordinalIndexOf(text, "", 3, 19));

        System.out.println(StrUtil.format("123{},{}", "a", ""));
    }

}
