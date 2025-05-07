package org.liu.thread.deepstudy.a0thread;

public class A4ReadStackLog {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        //开启两条线程，都竞争Blocked类的锁，一条线程拿到锁，另一条线程会被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
        //运行此程序后，在终端运行命令`jps`，找到当前类的运行id，再运行`jstack 运行id`，日志如stack.log
    }

    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
