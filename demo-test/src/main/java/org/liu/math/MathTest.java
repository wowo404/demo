package org.liu.math;

import java.io.UnsupportedEncodingException;

public class MathTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        int a = 0xffff;
        System.out.println(a);
    }

    private static int calculate(int n) {
        if (n == 0)
            return 1;
        return 2 * calculate(n - 1);
    }

    //获取byte数组后再转二进制是错误的
    public static void errorTransfer(){
        String name = "a我";
        byte[] bytes = name.getBytes();
        System.out.println(bytes.length);
        for (byte aByte : bytes) {
            Byte b = (Byte)aByte;
            System.out.println(b + "   ---   " + Integer.toHexString(b));
            //Integer.toBinaryString(b.intValue())
        }
    }

    /**
     * 16进制字符串转实际字符串
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static void test(){
        //定义一个十进制值
        int valueTen = 328;
        //将其转换为十六进制并输出
        String strHex = Integer.toHexString(valueTen);
        System.out.println(valueTen + " [十进制]---->[十六进制] " + strHex);
        //将十六进制格式化输出
        String strHex2 = String.format("%08x",valueTen);
        System.out.println(valueTen + " [十进制]---->[十六进制] " + strHex2);

        System.out.println("==========================================================");
        //定义一个十六进制值
        String strHex3 = "00001322";
        //将十六进制转化成十进制
        int valueTen2 = Integer.parseInt(strHex3,16);
        System.out.println(strHex3 + " [十六进制]---->[十进制] " + valueTen2);

        System.out.println("==========================================================");
        //可以在声明十进制时，自动完成十六进制到十进制的转换
        int valueHex = 0x00001322;
        System.out.println("int valueHex = 0x00001322 --> " + valueHex);
    }

    //十进制转十六进制？？？？古怪的方法
    private static String intToHex(int n) {
        //StringBuffer s = new StringBuffer();
        StringBuilder sb = new StringBuilder(8);
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            sb = sb.append(b[n%16]);
            n = n/16;
        }
        a = sb.reverse().toString();
        return a;
    }

}
