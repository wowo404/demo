package org.liu.binary;

import com.google.common.primitives.Bytes;

import java.io.ByteArrayOutputStream;

public class BinaryToHexadecimal {

    private static String hexString = "0123456789ABCDEF";

    public static void main(String[] args) {
        String serialNum = "87654321";
        String encode = encode(serialNum);//转成十六进制表示的字符串
        System.out.println(encode);
        //案例一：命令为停止，发送数据，起始符+TLV数据为7E000000，计算出的校验和为00，最终数据为7E000000007E
        //案例二：命令为读取采集模快，返回数据，起始符+TLV数据为7E030600040201030201，计算出的校验和为16，最终数据为7E030600040201030201167E
        //案例三：命令为读取采集模块序列号，返回数据，起始符+TLV数据为7E020800A4FFFFFFA4FFFFFFAE7E
        String ori = decode("020800" + encode);//加上TL转成原生的字符串
        System.out.println(ori + "--" + ori.length());
        byte[] bytes = ori.getBytes();
        System.out.println(bytesToHex(bytes));
        byte[] testB = "中".getBytes();
        System.out.println(bytesToHex(testB));
        //计算校验和FCS
        byte sum = sum(bytes);
        System.out.println(Integer.toBinaryString(sum));//11111111111111111111111110101110
        System.out.println(Integer.toHexString(sum));//ffffffae
        byte[] dateTime = hexStringToByte("20190904185401");
        System.out.println(dateTime);
        System.out.println(bytesToHex(dateTime));
        byte[] temp = new byte[]{0x02, 0x22};
        String s = bytesToHex(temp);
        System.out.println(s);
        get0x02();
    }

    public static void get0x02(){
        byte[] bytes = transferDateTime("20190909125959");
        for (byte aByte : bytes) {
            String s = Integer.toHexString(aByte);
            System.out.println(s);
        }
        int a = 45;
        System.out.println(Integer.toHexString(a));

    }

    public static byte[] transferDateTime(String dateTime) {
        int year = Integer.parseInt(dateTime.substring(0, 4));
        byte[] yearBytes = BinaryTest.intToByteArray(year);
        byte[] yearBytesTwo = new byte[2];
        yearBytesTwo[0] = yearBytes[3];
        yearBytesTwo[1] = yearBytes[2];
        byte[] surplusBytes = new byte[5];
        String otherStr = dateTime.substring(4);
        for (int i = 0, j = 1; i < otherStr.length(); i += 2, j+=2) {
            String other = otherStr.charAt(i) + "" + otherStr.charAt(j);
            int otherInt = Integer.parseInt(other);
            byte[] otherBytes = BinaryTest.intToByteArray(otherInt);
            surplusBytes[i / 2] = otherBytes[3];
        }
        return Bytes.concat(yearBytesTwo, surplusBytes);
    }

    public static void get0x10(){
        String serialNum = "87654321";
        byte sum1 = sum(serialNum.getBytes());//这个sum是以序列号来做的，不带TL两部分数据
        byte[] checksum = getChecksum(sum1);
        System.out.println(bytesToHex(checksum));//A4FFFFFFA4FFFFFF

        String tl = decode("020800");//加上TL转成原生的字符串
        byte[] tlBytes = tl.getBytes();
        byte[] concat = Bytes.concat(tlBytes, checksum);

        //计算校验和FCS
        byte sum = sum(concat);

        byte[] fcs = new byte[]{sum};
        byte[] concat1 = Bytes.concat(concat, fcs);
    }

    public static byte sum(byte[] bytes) {
        byte result = 0;
        for (byte aByte : bytes) {
            result += aByte;
        }
        return result;
    }

    public static byte[] getChecksum(byte code) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            int b = code >> i * 8;
            bytes[i] = (byte) b;
        }
        return bytes;
    }

    /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(String str) {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    /*
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     */
    public static String decode(String bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
                    .indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }

    /**
     * 将字节流转成十六进制字符串
     * 这是另一篇文章，有三种方式：https://blog.csdn.net/worm0527/article/details/69939307/
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static char[] HEX_VOCABLE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 字节数组转16进制字符串
     *
     * @param bs
     * @return
     */
    public static String bytesToHex(byte[] bs) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bs) {
            int high = (b >> 4) & 0x0f;
            int low = b & 0x0f;
            sb.append(HEX_VOCABLE[high]);
            sb.append(HEX_VOCABLE[low]);
        }
        return sb.toString();
    }
    /**
     * 将16进制字符转换为字节
     *
     * @param c
     * @return
     */
    public static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }
    /**
     * 16进制字符串转换成字节数组
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    //？？？直接输出就可以十六进制转十进制，为什么要这么转？？？？
    public static void hexToTen() {
        String str = "0123456789ABCDEF";
        int data = 0xee; //十六进制数
        System.out.println(data);
        int scale = 10; //转化目标进制

        String s = "";
        while (data > 0) {
            if (data < scale) {
                s = str.charAt(data) + s;
                data = 0;
            } else {
                int r = data % scale;
                s = str.charAt(r) + s;
                data = (data - r) / scale;
            }
        }

        System.out.println(s);
    }

}
