package org.liu.binary;

import java.io.UnsupportedEncodingException;

public class BineryTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        binary();
//        hex();
//        charAndInt();
    }

    //用int表示一个二进制数据
    //char也能表示一个二进制数据，对应编码表中的字符，0-127就可以直接查ASCII表，其他的要查Unicode表
    public static void binary(){
        int one = 0b00000000;//0
        char oneChar = 0b00000000;
        System.out.println(one + "--" + oneChar);
        int two = 0b00000001;//1
        char twoChar = 0b00000001;
        System.out.println(two + "--" + twoChar);
        int three = 0b01111111;//127
        char threeChar = 0b01111111;
        System.out.println(three + "--" + threeChar);
        int four = 0b0111111100000000;//8323072
        char fourChar = 0b0111111100000000;
        int fourCharToInt = fourChar;//char和int可以直接转换的
        System.out.println(four + "--" + fourChar + "--" + fourCharToInt);
        int five = 0b01111111111111111111111111111111;//2147483647
        int fiv8 = 0b10000000000000000000000000000000;//-2147483648
        char fiveChar = (char) five;//一个超过0-65535范围的数字可以强制转成char，但显示为空或者是乱码
        System.out.println(five + "--" + fiveChar);
    }

    //用int表示一个十六进制的数据
    public static void hex(){
        //十六进制2位
        int one = 0x3A;//58
        char oneChar = 0x3A;//:
        System.out.println(one + "--" + oneChar);
        int two = 0x00F0;//240
        char twoChar = 0x00F0;//ð
        System.out.println(two + "--" + twoChar);
        int three = 65535;
        char threeChar = (char) three;//￿
        System.out.println(three + "--" + threeChar);
        //十六进制4位7000-7FFF
        int five = 0x7000;
        char fiveChar = 0x7000;//瀀
        System.out.println(five + "--" + fiveChar);
        int six = 0x27FFF;//163839
        char sixChar = (char) 0x27FFF;//翿
        System.out.println(six + "--" + sixChar);
    }

    //如何理解char：https://blog.csdn.net/QGJava/article/details/5726840
    public static void charAndInt(){
        //char对应Unicode表，取值范围是[0, 65535],可以把这个范围的整数直接赋值给它
        char tes = 65535;
        System.out.println(tes);
        int a = Integer.MAX_VALUE;
        char aChar = (char)a;//当一个超过65535的数字强制转换成char时，就会变成65535
        int charToInt = aChar;//又变成了65535
        System.out.println(aChar + "--" + charToInt);
    }

}
