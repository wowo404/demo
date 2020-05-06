package org.liu.io;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class IOTest {

    private static final String path = "D:\\projects\\manufacture\\VarInfoLog(2019-12-31).txt";

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        readByChannelMap();
        System.out.println(System.currentTimeMillis() - start);
    }

    //读取一个50MB的文件，耗时1742ms
    public static void readStream() throws IOException {
        InputStream is = new FileInputStream(new File(path));
        //每次读取4096个字节
        byte[] buff = new byte[4096];
        int readLength;
        while ((readLength = is.read(buff, 0, buff.length)) != -1) {
            if (readLength < 4096) {
                byte[] subarray = ArrayUtils.subarray(buff, 0, readLength);
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
                byte[] subarray = ArrayUtils.subarray(buff, 0, readLength);
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
                byte[] subarray = ArrayUtils.subarray(buff, 0, readLength);
                System.out.println(new String(subarray));
            } else {
                System.out.println(new String(buff));
            }
        }
        dis.close();
    }

    //使用FileReader读取一个50MB的文件，耗时3688ms，从FileReader的构造方法中可以看出，FileReader使用的仍然是FileInputStream
    public static void fileReader() throws IOException {
        FileReader reader = new FileReader(new File(path));
        char[] buff = new char[8192];
        int readLength;
        while ((readLength = reader.read(buff, 0, buff.length)) != -1) {
            if (readLength < 8192) {
                char[] subarray = ArrayUtils.subarray(buff, 0, readLength);
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
                char[] subarray = ArrayUtils.subarray(buff, 0, readLength);
                System.out.println(new String(subarray));
            } else {
                System.out.println(new String(buff));
            }
        }
        reader.close();
    }

    //使用FileChannel读取一个50MB的文件，耗时1305ms
    public static void readByChannel() throws IOException {
        FileChannel channel = new FileInputStream(new File(path)).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        while (channel.read(buffer) != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array()));
            buffer.clear();
        }
        channel.close();
    }

    //使用FileChannel的map方法读取一个50MB的文件，耗时1382ms
    public static void readByChannelMap() throws IOException {
        FileChannel fc = new FileInputStream(new File(path)).getChannel();
        MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();
        byte[] buff = new byte[8192];
        while (byteBuffer.hasRemaining()) {
            int length = Math.min(byteBuffer.remaining(), 8192);
            byteBuffer.get(buff, 0, length);
            System.out.println(new String(buff));
        }
        fc.close();
    }

}
