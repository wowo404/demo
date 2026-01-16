package org.liu.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
            server.configureBlocking(false); // 设置为非阻塞模式，必须是非阻塞模式，因为在阻塞模式下，注册操作是不允许的
            server.register(selector, SelectionKey.OP_ACCEPT); // 将 ServerSocketChannel 注册到 Selector 上
            //臭名昭著的Epoll Bug ,它会导致Selector空轮询，最终导致CPU100%.直到JDK1.7版本该问题仍旧存在
            while (true) {
                selector.select();//select是阻塞方法，如果有新的连接请求，select()方法会返回，否则会一直阻塞
                System.out.println("receive new connection");
                // select(timeout)是非阻塞式，即等待timeout时间后返回
//                if (selector.select(2000) == 0) {
//                    System.out.println("非阻塞式调用select，这里可以做些其他的事情");
//                    continue;
//                }
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
                        client.socket().setKeepAlive(true);//保持长连接
                        client.register(selector, SelectionKey.OP_READ); // 将 SocketChannel 注册到 Selector 上
                        System.out.println(Thread.currentThread().getId() + ":" + client.getRemoteAddress());
                    }
                    if (key.isReadable()) {
                        // ...read messages...
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        while (true) {
                            buffer.clear();
                            int read = channel.read(buffer);
                            if (read <= 0) {
                                break;
                            }
                            buffer.flip();
                            //将buffer中的内容写入文件或者其他地方
                            //也可以向client写入内容
                            byte[] dst = new byte[buffer.position() + 1];
                            buffer.get(dst, 0, dst.length);
                            System.out.println(Thread.currentThread().getId() + ":" + new String(dst));
                        }
                        channel.register(selector, SelectionKey.OP_WRITE);
                    }
                    if (key.isWritable()) {
                        String returnMsg = LocalDateTime.now() + "-i got you message";
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.wrap(returnMsg.getBytes());
                        channel.write(byteBuffer);
                        key.interestOps(SelectionKey.OP_READ);
                        System.out.println("向客户端写入数据：" + returnMsg);
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
