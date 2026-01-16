package org.liu.basic;

/**
 * 使用javac CLPreparation编译成class文件
 * 再使用javap -v CLPreparation查看class文件，a和c变量需要额外使用putstatic指令初始化
 */
public class CLPreparation {
    public static int a = 100;
    public static final int b = 1000;
    public static final Integer c = 10000;
}
