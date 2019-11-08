package org.liu.basic;

public class TypeConversion {

    public static void main(String[] args) {
        intToShort();
    }

    public static void intToShort(){
        Integer a = 65535;
        System.out.println(a.shortValue());
        System.out.println(Integer.toHexString(65535));//两个字节
        System.out.println(Integer.toHexString(65536));//需要三个字节
    }

}
