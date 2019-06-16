package org.liu.multithread;

import java.util.concurrent.CountDownLatch;

/**
 * 倒数计数器
 *
 * @author liuzhangsheng
 * @create 2019/4/11
 */
public class TestCountDownLatch {

    public void test() throws InterruptedException {
        int len = 10;
        CountDownLatch countDownLatch = new CountDownLatch(len);
        for (int i = 0; i < len; i++) {
            new Thread(() -> {
                System.out.println("线程：" + Thread.currentThread().getName() + "，开始处理业务");
                try {
                    Thread.sleep(4 * 1000L);//模拟业务处理时间4秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程：" + Thread.currentThread().getName() + "，处理完毕");
                countDownLatch.countDown();
            }).start();//一定不要忘记调用start方法
        }
        //主线程阻塞，直到CountDownLatch的所有子线程完成
        countDownLatch.await();
        System.out.println("所有线程都执行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        TestCountDownLatch test = new TestCountDownLatch();
        test.test();
    }

}
