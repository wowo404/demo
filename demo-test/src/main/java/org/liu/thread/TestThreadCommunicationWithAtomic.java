package org.liu.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadCommunicationWithAtomic {

    private AtomicInteger holder = new AtomicInteger(0);

    public void test(){
        for (int i = 0; i < 10; i++) {
            new Thread(this::getLock).start();
        }
    }

    public void getLock(){
        while (true) {
            if (holder.get() == 0) {
                holder.getAndIncrement();//这种方式是有问题的，因为if中get判断和getAndIncrement在两个地方做，正确的做法是用cas来操作
                System.out.println(Thread.currentThread().getName() + " get the lock, the clock is " + holder.get());
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                holder.getAndDecrement();
                break;
            } else {
                try {
                    System.out.println(Thread.currentThread().getName() + " can not get the lock, sleep 100ms, the clock is " + holder.get());
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TestThreadCommunicationWithAtomic test = new TestThreadCommunicationWithAtomic();
        test.test();
    }

}
