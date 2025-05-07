package org.liu.thread.deepstudy.a4lock;

import java.util.concurrent.locks.Lock;

public class SelfLockTest {

    public static void main(String[] args) throws InterruptedException {
        SelfLockTest test = new SelfLockTest();
        Thread A = new Thread(() -> {
            test.getLock();
        }, "A");
        Thread B = new Thread(() -> {
            test.getLock();
        }, "B");
        A.start();
        Thread.sleep(100);
        B.start();
    }

    private Lock lock = new SelfLock();

    private void getLock() {
        lock.lock();
        try {
            System.out.println("thread:" + Thread.currentThread().getName() + " getLock.");
            while (true) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
