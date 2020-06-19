package org.liu.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AIOFutureFileReader {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        AIOFutureFileReader reader = new AIOFutureFileReader();
        reader.read();
    }

    public void read() throws IOException, ExecutionException, InterruptedException {
        Path path = Paths.get("D:\\work\\workspace-idea\\demo\\demo-test\\src\\main\\resources\\test.el");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> future = channel.read(buffer,0);
//        while (!future.isDone()){
//            System.out.println("I'm idle");
//        }
        Integer readNumber = future.get();

        buffer.flip();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
        decoder.decode(buffer,charBuffer,false);
        charBuffer.flip();
        String data = new String(charBuffer.array(),0, charBuffer.limit());
        System.out.println("read number:" + readNumber);
        System.out.println(data);
    }

}
