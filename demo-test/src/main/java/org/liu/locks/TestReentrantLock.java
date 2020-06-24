package org.liu.locks;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

    public static void main(String[] args) {
        TestReentrantLock test = new TestReentrantLock();
        test.unfairLock();
    }

    public void fairLock(){
        ReentrantLock lock = new ReentrantLock(true);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                lock.lock();

                try {
                    Thread.sleep(100L);
                    System.out.println(Thread.currentThread().getName() + "--" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }

    public void unfairLock(){
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                lock.lock();

                try {
                    Thread.sleep(100L);
                    System.out.println(Thread.currentThread().getName() + "--" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }

}
