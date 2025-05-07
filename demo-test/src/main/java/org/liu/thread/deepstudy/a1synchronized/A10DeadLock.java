package org.liu.thread.deepstudy.a1synchronized;

public class A10DeadLock {

    private static final String a = "a";
    private static final String b = "b";

    public static void main(String[] args) {
        new A10DeadLock().deadLock();
    }

    private void deadLock() {
        Thread A = new Thread() {
            public void run() {
                synchronized (a) {
                    try {
                        System.out.println("线程A拿a锁");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (b) {
                        System.out.println("线程A拿b锁");
                    }
                }
            }
        };
        Thread B = new Thread() {
            public void run() {
                synchronized (b) {
                    System.out.println("线程B拿b锁");
                    synchronized (a) {
                        System.out.println("线程B拿a锁");
                    }
                }
            }
        };
        A.start();
        B.start();
    }

}
