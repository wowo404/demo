package org.liu.binary;

import java.io.UnsupportedEncodingException;

public class Endian {

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test1(){
        float a = 10040.000005f;
        byte[] bytes = BinaryTest.float2byte(a);
        float toFloat = BinaryTest.byte2float(bytes, 0);
        byte[] bytes1 = BinaryTest.float2Byte2(a);
        System.out.println(toFloat);
    }

    public static void test() throws UnsupportedEncodingException {
        int testEndian = 0x001000;
        System.out.println(Integer.toBinaryString(testEndian));//00010010 00110100 01010110 01111000
        int reverseBytesInt = Integer.reverseBytes(testEndian);
        System.out.println(Integer.toHexString(reverseBytesInt));
        System.out.println(Integer.toBinaryString(reverseBytesInt));//1111000 01010110 00110100 00010010
        byte[] bytes = "123".getBytes("UTF-8");
        System.out.println(bytes);
        System.out.println(new String(bytes));
    }

}
