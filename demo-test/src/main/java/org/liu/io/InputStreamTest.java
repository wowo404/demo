package org.liu.io;

import cn.hutool.core.util.ArrayUtil;

import java.io.*;

public class InputStreamTest {

    private static final String path = "D:\\projects\\manufacture\\VarInfoLog(2019-12-31).txt";

    public static void main(String[] args) {

    }

    //读取一个50MB的文件，耗时1742ms
    public static void readStream() throws IOException {
        InputStream is = new FileInputStream(new File(path));
        //每次读取4096个字节
        byte[] buff = new byte[4096];
        int readLength;
        while ((readLength = is.read(buff, 0, buff.length)) != -1) {
            if (readLength < 4096) {
                byte[] subarray = ArrayUtil.sub(buff, 0, readLength);
                System.out.println(new String(subarray));
            } else {
                System.out.println(new String(buff));
            }
        }
        is.close();
    }

    //使用buffered stream读取一个50MB的文件，设置缓存buff为2048字节时耗时1358ms，设置为跟BufferedInputStream默认一样的8192时耗时1035ms
    public static void buffReadStream() throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(new File(path)));
        //每次读取8192个字节
        byte[] buff = new byte[8192];
        int readLength;
        while ((readLength = is.read(buff, 0, buff.length)) != -1) {
            if (readLength < 8192) {
                byte[] subarray = ArrayUtil.sub(buff, 0, readLength);
                System.out.println(new String(subarray));
            } else {
                System.out.println(new String(buff));
            }
        }
        is.close();
    }

    //使用DataInputStream读取一个50MB的文件，耗时1032ms
    public static void dataInputStream() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(new File(path)));
        //每次读取8192个字节
        byte[] buff = new byte[8192];
        int readLength;
        while ((readLength = dis.read(buff, 0, buff.length)) != -1) {
            if (readLength < 8192) {
                byte[] subarray = ArrayUtil.sub(buff, 0, readLength);
                System.out.println(new String(subarray));
            } else {
                System.out.println(new String(buff));
            }
        }
        dis.close();
    }

}
