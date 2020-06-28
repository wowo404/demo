package org.liu.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ChatClient {

    private SocketChannel socketChannel;
    private static final String ip = "127.0.0.1";
    private static final int port = 9999;

    public ChatClient() {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            if (!socketChannel.connect(new InetSocketAddress(ip, port))){
                socketChannel.finishConnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            if ("bye".equals(message)){
                socketChannel.close();
                return;
            }
            message = socketChannel.getLocalAddress() + ":" + message;
            socketChannel.write(ByteBuffer.wrap(message.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage(){
        new Thread(() -> {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (true) {
                    buffer.clear();
                    int read = socketChannel.read(buffer);
                    if (read > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        chatClient.readMessage();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            chatClient.sendMessage(nextLine);
        }
    }

}
