package org.liu.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockCondition {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    static class TestLockConditionThread implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                condition.await();
                System.out.println(Thread.currentThread().getId() + "线程被唤醒后继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new TestLockConditionThread());
        t1.start();
        Thread.sleep(2000);//让子线程先跑，也可以调用t1.join()方法，此方法更好
        lock.lock();
        condition.signal();
        lock.unlock();
    }

}
