package org.liu.thread.deepstudy.a0thread;

import java.util.concurrent.TimeUnit;

public class A9ThreadInterrupted {

    public static void main(String[] args) throws InterruptedException {
        SleepThread sleepThread = new SleepThread("sleepThread");
        BusyThread busyThread = new BusyThread("busyThread");
        //如果没有setDaemon(true)，运行此程序后，两个线程均不会主动退出，然后通过jps加jstack命令可以看到，两个线程依然存在
        //SleepThread处于TIMED_WAITING状态，BusyThread处于RUNNABLE状态
        //TODO:为什么会这样？
        sleepThread.setDaemon(true);
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        //主线程停5s，让子线程充分运行起来
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        //sleep方法响应中断，肯定会中断sleep。在抛出异常之前，会清理掉当前线程的中断标志，因此返回false，因为当前线程已经停止了。
        System.out.println("SleepThread is interrupted:" + sleepThread.isInterrupted());
        //BusyThread没有立即响应中断，只是他的中断标志位已经标识为被中断，那么此时调用isInterrupted返回true。
        System.out.println("BusyThread is interrupted:" + busyThread.isInterrupted());

        TimeUnit.SECONDS.sleep(5);
    }

    static class SleepThread extends Thread {

        public SleepThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            //让此线程一直处于休眠状态
            while (true) {
                try {
                    //interrupt中断sleep：先清除标识，后抛异常
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    System.out.println("========");
                }
            }
        }
    }

    static class BusyThread extends Thread {

        public BusyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            //此线程一直处于工作状态
            while (true) {
            }
        }
    }

}
