package org.liu.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

    private static final String path = "D:\\projects\\manufacture\\VarInfoLog(2019-12-31).txt";

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        readByChannelMap();
        System.out.println(System.currentTimeMillis() - start);
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
