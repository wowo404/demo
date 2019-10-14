package org.liu.binary;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.text.NumberFormat;
import java.util.ArrayList;

public class BinaryTest {

    public static void main(String[] args) {
        int a = 0x003F;
        System.out.println(a);
        int i = Integer.parseInt("10000000", 2);
        System.out.println(i);
        System.out.println(Integer.toHexString(i));
        byte c = (byte) i;
        System.out.println(c);
        byte b = Byte.parseByte("10010001", 2);
        System.out.println(b);
    }

    //int也可以用二进制，八进制，十进制，十六进制来表示
    //char也能表示一个二进制数据，对应编码表中的字符，0-127就可以直接查ASCII表，其他的要查Unicode表
    public static void intAndRadix() {
        int zeroTwo = 0b01111110;
        int zeroEight = 0176;
        int zeroTen = 126;
        int zeroSixteen = 0x7E;
        System.out.println("十六进制：" + zeroSixteen + ",十进制：" + zeroTen + "，八进制：" + zeroEight + "，二进制：" + zeroTwo);
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
        int max = 0x7fffffff;//2147483647
        int min = 0x80000000;//-2147483648
        int five = 0b01111111111111111111111111111111;//2147483647，这是用int来表示的最大的二进制
        int fiv8 = 0b10000000000000000000000000000000;//-2147483648，这是用int来表示的最小的二进制
        char fiveChar = (char) five;//一个超过0-65535范围的数字可以强制转成char，但显示为空或者是乱码
        System.out.println(five + "--" + fiveChar);
        int a = 0x0409;
//        a = 255;
        System.out.println(a);
        System.out.println(Integer.toHexString(1033));
        a = 0x11223344;
        System.out.println(Integer.toBinaryString(a));
    }

    public static void longAndRadix() {
        long zeroTwo = 0b01010101;
        long zeroEight = 0176;
        long zeroTen = 2147483648L;//超过2147483647就必须加L
        long zeroSixteen = 0x74984;
        System.out.println("十六进制：" + zeroSixteen + ",十进制：" + zeroTen + "，八进制：" + zeroEight + "，二进制：" + zeroTwo);
    }

    private static final BigDecimal EIGENVALUE_DIVIDEND = new BigDecimal("32768.0");

    public static BigDecimal parseIntEigenvalue(int intEigenvalue, double range) {
        //得到的数据*量程/32768.0
        BigDecimal calculate = new BigDecimal(intEigenvalue, MathContext.DECIMAL64);
        return calculate.multiply(new BigDecimal(range)).divide(EIGENVALUE_DIVIDEND, 10, RoundingMode.HALF_UP);
    }

    public static byte[] double2Bytes(double d) {
        long value = Double.doubleToRawLongBits(d);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    public static double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }

    public static double getDoubleWhenBytesLengthEquals4(byte[] bytes) {
        byte[] temp = new byte[8];
        temp[0] = 0x00;
        temp[1] = 0x00;
        temp[2] = 0x00;
        temp[3] = 0x00;
        temp[4] = bytes[0];
        temp[5] = bytes[1];
        temp[6] = bytes[2];
        temp[7] = bytes[3];
        return getDouble(temp);
    }

    public static double getDouble(byte[] bytes) {
        long l = getLong(bytes);
        System.out.println(l);
        return Double.longBitsToDouble(l);
    }

    public static long getLong(byte[] bytes) {
        return (0xff00000000000000L & ((long) bytes[0] << 56)
                | (0xff000000000000L & ((long) bytes[1] << 48))
                | (0xff0000000000L & ((long) bytes[2] << 40))
                | (0xff00000000L & ((long) bytes[3] << 32))
                | (0xff000000L & ((long) bytes[4] << 24))
                | (0xff0000L & ((long) bytes[5] << 16))
                | (0xff00L & ((long) bytes[6] << 8))
                | (0xffL & (long) bytes[7]));
    }

    public static void shortAndRadix() {
        short zeroTwo = 0b111111111111111;
        short zeroEight = 0176;
        short zeroTen = 32767;
        short zeroSixteen = 0x7e;
        System.out.println("十六进制：" + zeroSixteen + ",十进制：" + zeroTen + "，八进制：" + zeroEight + "，二进制：" + zeroTwo);
        short maxShort = Short.MAX_VALUE;//32767,111111111111111
        short minShort = Short.MIN_VALUE;//-32768,11111111111111111000000000000000
        System.out.println(Integer.toBinaryString(maxShort));
        System.out.println(Integer.toBinaryString(minShort));
    }

    public static void floatAndRadix() {
        Float zero = -12.5f;
        float zeroTwo = 0b11000001010010000000000000000000;
        float zeroEight = 0176;
        float zeroTen = 32767;
        float zeroSixteen = 0x7e;
        System.out.println("十六进制：" + zeroSixteen + ",十进制：" + zeroTen + "，八进制：" + zeroEight + "，二进制：" + zeroTwo);
        System.out.println(zero.equals(zeroTwo));//???
    }

    public static void doubleAndRadix() {
        //1100 0001 0100 1000 0000 0000 0000 0000
        double zeroTwo = 0b11000001010010000000000000000000;
        double zeroEight = 0176;
        double zeroTen = 32767;
        double zeroSixteen = 0x7e;
        System.out.println("十六进制：" + zeroSixteen + ",十进制：" + zeroTen + "，八进制：" + zeroEight + "，二进制：" + zeroTwo);
    }

    //char也可以用二进制，八进制，十进制，十六进制来表示
    public static void charAndRadix() {
        char zeroTwo = 0b01111110;
        char zeroEight = 0176;
        char zeroTen = 126;
        char zeroSixteen = 0x7E;
        //这样打印就会打印出char在Unicode表中对应的字符
        System.out.println("十六进制：" + zeroSixteen + ",十进制：" + zeroTen + "，八进制：" + zeroEight + "，二进制：" + zeroTwo);
        //十六进制2位
        int zero = 0x7E;
        System.out.println(zero);
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
        System.out.println(Character.MAX_VALUE);
    }

    //byte也可以用数字的二进制，八进制，十进制，十六进制表示
    public static void byteAndRadix() {
        byte zeroSixteen = 0x7E;
        byte zeroTen = 126;
        byte zeroEight = 0176;
        byte zeroTwo = 0b01111110;
        System.out.println("十六进制：" + zeroSixteen + ",十进制：" + zeroTen + "，八进制：" + zeroEight + "，二进制：" + zeroTwo);
        byte[] b = "~".getBytes();
        if (b[0] == 126) {//这是相等的
            System.out.println(true);
        }
    }

    //如何理解char：https://blog.csdn.net/QGJava/article/details/5726840
    public static void charAndInt() {
        //char对应Unicode表，取值范围是[0, 65535],可以把这个范围的整数直接赋值给它
        char tes = 65535;
        System.out.println(tes);
        int a = Integer.MAX_VALUE;
        char aChar = (char) a;//当一个超过65535的数字强制转换成char时，就会变成65535
        int charToInt = aChar;//又变成了65535
        System.out.println(aChar + "--" + charToInt);
    }

    //byte数组与int的相互转换，byte数组必须为4个字节长度，因为int的长度是4
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    public static byte[] intToBytes2(int n) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (n >> (24 - i * 8));
        }
        return b;
    }

    public static byte[] float2Byte2(float data) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putFloat(data);
        byte[] bBuffer = buffer.array();
        return dataValueRollback(bBuffer);
    }

    //数值反传
    private static byte[] dataValueRollback(byte[] data) {
        ArrayList<Byte> al = new ArrayList<Byte>();
        for (int i = data.length - 1; i >= 0; i--) {
            al.add(data[i]);
        }

        byte[] buffer = new byte[al.size()];
        for (int i = 0; i <= buffer.length - 1; i++) {
            buffer[i] = al.get(i);
        }
        return buffer;
    }

    /**
     * 浮点转换为字节
     *
     * @param f
     * @return
     */
    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }

        return dest;

    }

    /**
     * 字节转换为浮点
     *
     * @param b     字节（至少4个字节）
     * @param index 开始位置
     * @return
     */
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }


}
