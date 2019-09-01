package org.liu.binary;

import java.io.ByteArrayOutputStream;

public class BinaryToHexadecimal {

    private static String hexString = "0123456789ABCDEF";
    public static void main(String[] args) {
        String encode = encode("~");
        System.out.println(encode);
        //案例一：命令为停止，发送数据，起始符+TLV数据为7E000000，计算出的校验和为00，最终数据为7E000000007E
        //案例二：命令为读取采集模快，返回数据，起始符+TLV数据为7E030600040201030201，计算出的校验和为16，最终数据为7E030600040201030201167E
        String ori = decode("030600040201030201");
        System.out.println(ori + "---" + ori.length());
        byte[] bytes = ori.getBytes();
        System.out.println(bytesToHexString(bytes));
        //计算校验和FCS
        byte sum = sum(bytes);
        System.out.println(Integer.toHexString(sum));
        //0x7e 0x03 0x06 0x00 0x04 0x02 0x01 0x03 0x02 0x01校验和 0x7e
    }

    public static byte sum(byte[] bytes){
        byte result = 0;
        for (byte aByte : bytes) {
            result += aByte;
        }
        return result;
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
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++ ) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
