package org.liu.io;

import cn.hutool.core.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReaderTest {

    private static final String path = "D:\\projects\\manufacture\\VarInfoLog(2019-12-31).txt";

    public static void main(String[] args) {

    }

    //使用FileReader读取一个50MB的文件，耗时3688ms，从FileReader的构造方法中可以看出，FileReader使用的仍然是FileInputStream
    public static void fileReader() throws IOException {
        FileReader reader = new FileReader(new File(path));
        char[] buff = new char[8192];
        int readLength;
        while ((readLength = reader.read(buff, 0, buff.length)) != -1) {
            if (readLength < 8192) {
                char[] subarray = ArrayUtil.sub(buff, 0, readLength);
                System.out.println(new String(subarray));
            } else {
                System.out.println(new String(buff));
            }
        }
        reader.close();
    }

    //使用BufferedReader读取一个50MB的文件，耗时1202ms
    public static void bufferedReader() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        char[] buff = new char[8192];
        int readLength;
        while ((readLength = reader.read(buff, 0, buff.length)) != -1) {
            if (readLength < 8192) {
                char[] subarray = ArrayUtil.sub(buff, 0, readLength);
                System.out.println(new String(subarray));
            } else {
                System.out.println(new String(buff));
            }
        }
        reader.close();
    }

}
