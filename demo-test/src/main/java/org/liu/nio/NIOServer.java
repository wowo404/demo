package org.liu.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;

public class NIOServer {

    private static final int port = 8742;

    public static void main(String[] args) {
        Selector selector = null;
        ServerSocketChannel server = null;
        try {
            selector = Selector.open(); // 打开 Selector
            server = ServerSocketChannel.open(); // 打开 ServerSocketChannel
            server.socket().bind(new InetSocketAddress(port)); // 绑定端口
            server.configureBlocking(false); // 设置为非阻塞模式
            server.register(selector, SelectionKey.OP_ACCEPT); // 将 ServerSocketChannel 注册到 Selector 上
            while (true) {
                selector.select();
                Iterator<SelectionKey> i = selector.selectedKeys().iterator();
                while (i.hasNext()) {
                    SelectionKey key = i.next();
                    i.remove();
                    if (key.isConnectable()) {
                        ((SocketChannel) key.channel()).finishConnect();
                    }
                    if (key.isAcceptable()) {
                        // accept connection
                        SocketChannel client = server.accept(); // 接受 TCP 连接
                        client.configureBlocking(false);
                        client.socket().setTcpNoDelay(true);
                        client.register(selector, SelectionKey.OP_READ); // 将 SocketChannel 注册到 Selector 上
                        System.out.println(Thread.currentThread().getId() + ":" + client.getRemoteAddress());
                    }
                    if (key.isReadable()) {
                        // ...read messages...
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        while (true) {
                            int read = channel.read(buffer);
                            if (read <= 0) {
                                break;
                            }
                            buffer.flip();
                            //将buffer中的内容写入文件或者其他地方
                            //也可以向client写入内容
                            System.out.println(Thread.currentThread().getId() + ":" +  new String(buffer.array()));
                        }
                        channel.register(selector, SelectionKey.OP_WRITE);
                    }
                    if (key.isWritable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.wrap((LocalDateTime.now() + "i got you message").getBytes());
                        channel.write(byteBuffer);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException("Server failure: " + e.getMessage());
        } finally {
            try {
                selector.close();
                server.socket().close();
                server.close();
//                stopped();
            } catch (Exception e) {
                // do nothing - server failed
            }
        }
    }
}
