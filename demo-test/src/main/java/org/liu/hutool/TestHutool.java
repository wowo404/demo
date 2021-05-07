package org.liu.hutool;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class TestHutool {

    public static void main(String[] args) {
        System.out.println(DateUtil.beginOfDay(new Date()));
        System.out.println(DateUtil.endOfDay(new Date()));
        System.out.println(DateUtil.endOfDay(DateUtil.offsetDay(new Date(), Integer.parseInt("10"))));
        System.out.println(String.format("abc%s,%s8888", 3, 4));
    }

}
