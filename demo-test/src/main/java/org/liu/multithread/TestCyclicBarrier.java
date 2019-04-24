package org.liu.multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 同步屏障
 *
 * @author liuzhangsheng
 * @create 2019/4/11
 */
public class TestCyclicBarrier {

    private final int len = 10;

    private final CyclicBarrier barrier = new CyclicBarrier(len, () -> {
        System.out.println("先执行我，再执行其他线程");
    });

    public void test(){
        for (int i = 0; i < len; i++) {
            new Thread(() -> {
                System.out.println("线程--" + Thread.currentThread().getName() + "--已到达屏障");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                //start business code
                System.out.println("开始执行业务代码，start time：" + System.nanoTime() + "，thread：" + Thread.currentThread().getName());
            }).start();
        }
    }

    public static void main(String[] args) {
        TestCyclicBarrier test = new TestCyclicBarrier();
        test.test();
    }

}
