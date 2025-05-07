package org.liu.thread.deepstudy.a5atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseAtomicTest {

    static AtomicInteger atomicInteger = new AtomicInteger(0);
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    atomicInteger.incrementAndGet();
                }
                countDownLatch.countDown();
            }
        }, "threadA");
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    atomicInteger.incrementAndGet();
                }
                countDownLatch.countDown();
            }
        }, "threadB");

        threadA.start();
        threadB.start();
        //实现方式一：主线程睡眠1s，让A、B两个子线程运行完毕
        Thread.sleep(1000);
        //实现方式二：A、B子线程分别调用join()方法
        threadA.join();
        threadB.join();
        //实现方式三：使用CountDownLatch
        countDownLatch.await();
        System.out.println("获取到期待的值，应该是14，实际为 " + atomicInteger.get());
    }

}
