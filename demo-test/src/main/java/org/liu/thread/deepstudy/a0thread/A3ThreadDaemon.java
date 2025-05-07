package org.liu.thread.deepstudy.a0thread;

public class A3ThreadDaemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread(), "daemonRunner");
        thread.setDaemon(true);//守护线程，不会执行finally块的代码
        thread.start();
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Daemon Thread finally run...");
            }
        }
    }

}
