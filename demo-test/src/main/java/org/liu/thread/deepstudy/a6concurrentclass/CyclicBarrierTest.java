package org.liu.thread.deepstudy.a6concurrentclass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    //使用的业务场景：针对业务完成（或所有的线程执行完成）之后放行（如赛跑运动中的终点）
    static CyclicBarrier barrier = new CyclicBarrier(3);
    //使用的业务场景：针对业务准备就绪（所有的线程到达了屏障）之后开始（如赛跑运动中的起跑线）
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        //对于这样的业务场景：需要子线程处理业务，主线程等待子线程完成任务后继续执行其他任务。
        //建议使用CountDownLatch，原因是CountDownLatch的语义较清晰，如需要两个子线程处理则传参为2，即new CountDownLatch(2)
        //且主线程调用等待的方法await()和子线程调用的countDown()方法不同，语义上较清晰
        //CyclicBarrier则不让，子线程和主线程都调用的await()方法，语义上没那么清晰
        useCyclicBarrier2();
        useCyclicBarrier();
        useCountDownLatch();
    }

    private static void useCountDownLatch() throws InterruptedException {
        int[] arr = new int[2];
        Thread threadA = new Thread(() -> {
            arr[0] = 3 * 5;
            countDownLatch.countDown();
        }, "threadA");
        Thread threadB = new Thread(() -> {
            arr[1] = 10 + 2;
            countDownLatch.countDown();
        }, "threadB");

        threadA.start();
        threadB.start();

        countDownLatch.await();

        System.out.println("CountDownLatch，A和B线程的计算结果汇总：" + (arr[0] + arr[1]));
    }

    private static void useCyclicBarrier() throws BrokenBarrierException, InterruptedException {
        int[] arr = new int[2];
        Thread threadA = new Thread(() -> {
            arr[0] = 3 * 5;
            try {
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }, "threadA");
        Thread threadB = new Thread(() -> {
            arr[1] = 10 + 2;
            try {
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }, "threadB");

        threadA.start();
        threadB.start();

        //不推荐这种使用方式，推荐useCyclicBarrier2()示例方法
        barrier.await();//主线程同样要作为等待的其中一个成员，不然后面的打印结果就会是0

        System.out.println("CyclicBarrier，A和B线程的计算结果汇总：" + (arr[0] + arr[1]));
    }

    private static void useCyclicBarrier2() throws BrokenBarrierException, InterruptedException {
        int[] arr = new int[2];
        //将子线程完成后，需要做的其他的任务放入此构造方法的第二个参数中
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("CyclicBarrier2，A和B线程的计算结果汇总：" + (arr[0] + arr[1]));
        });
        Thread threadA = new Thread(() -> {
            arr[0] = 3 * 5;
            try {
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }, "threadA");
        Thread threadB = new Thread(() -> {
            arr[1] = 10 + 2;
            try {
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }, "threadB");

        threadA.start();
        threadB.start();
    }

}
