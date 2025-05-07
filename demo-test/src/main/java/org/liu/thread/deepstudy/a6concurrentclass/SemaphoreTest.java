package org.liu.thread.deepstudy.a6concurrentclass;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程【" + Thread.currentThread().getName() + "】执行业务逻辑，如：获取数据库连接，读取文件等");
                semaphore.release();
            });
            thread.start();
        }
    }

}
