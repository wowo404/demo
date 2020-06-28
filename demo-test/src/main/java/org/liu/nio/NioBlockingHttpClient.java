package org.liu.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class NioBlockingHttpClient {
    private SocketChannel socketChannel;
    private static String host = "127.0.0.1";
    private static int port = 8742;


    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                NioBlockingHttpClient client = null;
                try {
                    client = new NioBlockingHttpClient();
                    client.request();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public NioBlockingHttpClient() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.socket().setSoTimeout(5000);
        SocketAddress remote = new InetSocketAddress(host, port);
        this.socketChannel.connect(remote);
    }

    public void request() throws IOException {
        PrintWriter pw = getWriter(socketChannel.socket());
        BufferedReader br = getReader(socketChannel.socket());

        pw.write(HttpUtil.compositeRequest(host + ":" + port));
        pw.flush();
        String msg;
        while ((msg = br.readLine()) != null){
            System.out.println(msg);
        }
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(out);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }

}
