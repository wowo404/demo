package org.liu.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class ChatServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private static final int port = 9999;

    public ChatServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端：" + socketChannel.getRemoteAddress() + "上线了");
                    }
                    if (key.isReadable()) {
                        readMessage(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            int length = socketChannel.read(buffer);
            if (length <= 0) {
                break;
            }
            buffer.flip();
            System.out.println("收到来自客户端：" + socketChannel.getRemoteAddress() + "的消息：" + new String(buffer.array()));
            sendMessage(socketChannel, buffer);
        }
    }

    private void sendMessage(SocketChannel exclude, ByteBuffer buffer) throws IOException {
        for (SelectionKey next : selector.keys()) {
            SelectableChannel channel = next.channel();
            if (channel instanceof SocketChannel && channel != exclude) {
                SocketChannel socketChannel = (SocketChannel) channel;
                socketChannel.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer().start();
    }

}
