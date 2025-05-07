package org.liu.thread.deepstudy.a0thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class A9PipeInOut {

    public static void main(String[] args) throws IOException {
        PipedReader in = new PipedReader();
        PipedWriter out = new PipedWriter();
        out.connect(in);
        Thread thread = new Thread(new PrintThread(in), "PrintThread");
        thread.start();
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }

    static class PrintThread implements Runnable {

        public PipedReader in;

        public PrintThread(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
            }
        }
    }

}
