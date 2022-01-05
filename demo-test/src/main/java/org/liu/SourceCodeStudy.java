package org.liu;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class SourceCodeStudy {

    public static void main(String[] args) throws InterruptedException {
        testStringBuilder2();
    }

    public static void testStringBuilder() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        CyclicBarrier cb = new CyclicBarrier(10);
        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {

            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                cdl.countDown();
                sb.append(finalI);
            });
            thread.start();
        }
        cdl.await();
        System.out.println(sb.toString());
    }

    public static void testStringBuilder2() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                sb.append(finalI);
            });
            thread.start();
        }
        Thread.sleep(2000L);
        System.out.println(sb.toString());
    }

}
