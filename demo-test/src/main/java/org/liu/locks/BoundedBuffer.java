package org.liu.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 改造自Condition官方文档中的示例，将put和take方法分别放入不同的thread
 */
public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            System.out.println(Thread.currentThread().getName() + "将" + x + "放入item");
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try {
            while (true) {
                if (count == 0) {
                    notEmpty.await();
                }
                System.out.println(Thread.currentThread().getName() + "被唤醒，开始从item中取东西");
                Object x = items[takeptr];
                if (++takeptr == items.length) takeptr = 0;
                --count;
                System.out.println(Thread.currentThread().getName() + "从item中取走一个:" + x);
                notFull.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    class PutThread implements Runnable {
        @Override
        public void run() {
            try {
                //在这里循环，或者在调用处循环
                for (int i = 0; i < 200; i++) {
                    put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class TakeThread implements Runnable {
        @Override
        public void run() {
            try {
                take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        new Thread(boundedBuffer.new PutThread()).start();
        new Thread(boundedBuffer.new TakeThread()).start();
    }

}
