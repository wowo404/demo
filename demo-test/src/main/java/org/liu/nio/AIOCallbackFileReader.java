package org.liu.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AIOCallbackFileReader {

    public static void main(String[] args) throws IOException, InterruptedException {
        AIOCallbackFileReader reader = new AIOCallbackFileReader();
        reader.read();
    }

    public void read() throws InterruptedException, IOException {
        Path path = Paths.get("D:\\work\\workspace-idea\\demo\\demo-test\\src\\main\\resources\\test.el");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println(Thread.currentThread().getName() + " read success!");
                System.out.println(new String(attachment.array()));
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("read error");
            }
        });

        while (true){
            System.out.println(Thread.currentThread().getName() + " sleep");
            Thread.sleep(1000);
        }
    }

}
