package org.liu.jvm;

import org.openjdk.jol.info.ClassLayout;

public class TestJvm {

    public static void main(String[] args) {
        printObjectMemoryLayoutInfo();
        printObjectArrayMemoryLayoutInfo();
    }

    /**
     * 打印对象内存布局信息
     */
    public static void printObjectMemoryLayoutInfo() {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    public static void printObjectArrayMemoryLayoutInfo() {
        Object[] obj = new Object[1];
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

}
