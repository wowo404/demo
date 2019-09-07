package org.liu.format;

import org.liu.binary.BinaryTest;
import org.liu.binary.BinaryToHexadecimal;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class StringFormatUtil {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String hexString = Integer.toHexString(4096);//65533
        if (hexString.length() < 4) {
            hexString = "000" + hexString;
        }
        System.out.println(hexString);
        String total = hexString.substring(hexString.length() - 4);
        System.out.println(total);
        String first = total.substring(0, 2);
        String second = total.substring(2);
        int firstInt = Integer.parseInt(first, 16);
        int secondInt = Integer.parseInt(second, 16);
        byte firstB = (byte) firstInt;
        byte secondB = (byte) secondInt;
//        String firstHex = singleByteToHex(firstB);
//        String secondHex = singleByteToHex(secondB);
        byte[] temp =new byte[4];
        temp[0] = 0x00;
        temp[1] = 0x00;
        temp[2] = (byte) firstInt;
        temp[3] = (byte) secondInt;
        int i = BinaryTest.byteArrayToInt(temp);
        System.out.println(i);
        byte[] bytes = BinaryTest.intToByteArray(4096);
        System.out.println(bytes);
        String bytesToHex = BinaryToHexadecimal.bytesToHex(temp);
        System.out.println(bytesToHex);
        System.out.println(Integer.parseInt(bytesToHex, 16));
    }

    public static String singleByteToHex(byte b){
        StringBuilder sb = new StringBuilder();
        int high = (b >> 4) & 0x0f;
        int low = b & 0x0f;
        sb.append(HEX_VOCABLE[high]);
        sb.append(HEX_VOCABLE[low]);
        return sb.toString();
    }

    private static char[] HEX_VOCABLE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void test(){
        int youNumber = 1;
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String str = String.format("%04d", youNumber);

        System.out.println(str); // 0001
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

    private static String hexString = "0123456789ABCDEF";

}
