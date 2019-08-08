package org.liu.echoserver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoSocketClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        EchoSocketClient client = new EchoSocketClient();
        client.test();
    }

    private void test() throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 4123);
        socket.setKeepAlive(true);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        new Thread(new PrintThread(inputStream)).start();
//        sendMsg(outputStream);
        //以CRLF结尾，表示一条消息发送完了
        outputStream.write("36475886\r\n".getBytes());
        outputStream.flush();
        outputStream.write("123456\r\n".getBytes());
        outputStream.flush();
        outputStream.write("hello\r\n".getBytes());
        outputStream.flush();
        outputStream.write("hello\r\n".getBytes());
        outputStream.flush();

        socket.shutdownInput();
        socket.shutdownOutput();
    }

    private void sendMsg(OutputStream outputStream) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            System.out.println(next);
            if ("exit".equals(next)) {
                outputStream.close();
                System.exit(0);
            } else {
                outputStream.write(next.getBytes());
                outputStream.flush();
            }
        }
    }

    public class PrintThread implements Runnable {
        private InputStream inputStream;

        public PrintThread(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            BufferedInputStream buffer = new BufferedInputStream(inputStream);
            try {
                while (true) {
                    byte[] temp = new byte[1024];
                    while (buffer.read(temp) != -1) {
                        String receive = new String(temp, "utf-8");
                        System.out.println(receive);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
