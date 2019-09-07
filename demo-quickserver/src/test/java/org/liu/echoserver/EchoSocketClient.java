package org.liu.echoserver;

import java.io.*;
import java.net.Socket;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EchoSocketClient {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    public static void main(String[] args) throws IOException, InterruptedException {
        EchoSocketClient client = new EchoSocketClient();
        client.testOneThread();
    }

    private void testOneThread() throws IOException {
        Socket socket = new Socket("127.0.0.1", 4123);
        //为什么接收input的消息，必须另起一个线程？？？？
        new Thread(new PrintThread(socket)).start();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        pw.write("liu");
//        pw.write("\r\n");
        pw.flush();

        pw.write("liu");
//        pw.write("\r\n");
        pw.flush();

        pw.write("hello");
//        pw.write("\r\n");
        pw.flush();

        pw.write("hello");
//        pw.write("\r\n");
        pw.flush();

        pw.write("ok");
//        pw.write("\r\n");
        pw.flush();

        pw.write("What's interest?");
//        pw.write("\r\n");
        pw.flush();
    }

    private void testTwoThread() throws IOException {
        Socket socket = new Socket("127.0.0.1", 4123);

        new Thread(new SendThread(socket)).start();
        new Thread(new PrintThread(socket)).start();
    }

    private void print(Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        br.lines().forEach(System.out::println);
        br.close();
    }

    private void test() throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 28000);

        new Thread(new PrintThread(socket)).start();

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        //以CRLF结尾，表示一条消息发送完了
        //登录
        pw.write("SYAL 1163647588612345678\r\n");
        pw.flush();

        //心跳
        //SYAH 1011
        //数据类型（4个字节）-空格（1个字节）-数据版本（1个字节）-数据长度（2个字节，小于10补0）-数据
        //pw.write("SYAH 1011\r\n");
        //pw.flush();

        String msg = "0001sendMsg "
                + sdf.format(new Date())
                + ", 0:"
                + (Math.random() * 100)
                + ", 1:"
                + (int) (30 + Math.random() * (100 + 1))
                + ", 2:"
                + (int) (1 + Math.random() * (80))
                + ", 3:"
                + (int) (40 + Math.random() * (150 + 1))
                + ", 4:"
                + (int) (1 + Math.random() * (60))
                + ", 5:"
                + (int) (40 + Math.random() * (120 + 1))
                + ", 6:"
                + (int) (10 + Math.random() * (50 + 1))
                + ", 7:"
                + (int) (20 + Math.random() * (40 + 1))
                + ", 8:"
                + (int) (1 + Math.random() * (90))
                + ", 9:"
                + (int) (15 + Math.random() * (40 + 1))
                + ", 10:"
                + (int) (1 + Math.random() * (30))
                + ", 36475886\r\n";

        //数据
        msg = "SYAD 1161510101012346541\r\n";

        pw.write(msg);
        pw.flush();
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

    public class SendThread implements Runnable {
        private Socket socket;

        public SendThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                pw.write("liu");
                pw.write("\r\n");
                pw.flush();
                pw.write("liu");
                pw.write("\r\n");
                pw.flush();
                pw.write("hello");
                pw.write("\r\n");
                pw.flush();
                pw.write("hello");
                pw.write("\r\n");
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class PrintThread implements Runnable {
        private Socket socket;

        public PrintThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                if (socket.isClosed()) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                try {
                    InputStream inputStream = socket.getInputStream();
                    if (inputStream.available() <= 0) {
                        System.out.println("没有数据可以读取");
                        return;
                    }
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    br.lines().forEach(msg -> {
                        try {
                            if (msg.contains("Auth failed")) {
                                socket.close();
                            }
                            if (msg.contains("-ERR Timeout")) {
                                socket.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(msg);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
