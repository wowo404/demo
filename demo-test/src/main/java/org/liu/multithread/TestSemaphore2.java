package org.liu.multithread;

import java.util.concurrent.Semaphore;

/**
 * 假设以下场景：汽车站发车，一辆车必须满5个人才能发车，同时满5个人才会给票。
 * 此场景跟TestSemaphore是有区别的，TestSemaphore是只要有一个空闲的信号量则会给其中一个线程。
 */
public class TestSemaphore2 {

    //8个线程，同时只能跑5个，剩下的3个排队
    public static void main(String[] args) throws InterruptedException {
        int N = 8; //工人数
        Semaphore semaphore = new Semaphore(5); //机器数目
        for (int i = 0; i < N; i++) {
            new Worker(i, semaphore).start();
        }
        //此示例代码是从极客时间课程（java核心技术面试精讲）中看到的，但课程中此处代码是错误的，这里我改正确了
        Thread.sleep(1000);
        System.out.println("释放第一批（5个）信号量");
        semaphore.release(5);
        Thread.sleep(1000);
        System.out.println("释放第二批（3个）信号量");
        semaphore.release(3);
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人" + this.num + "占用一个机器在生产...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //此场景下则不能在每一个线程内部释放信号量
//            finally {
//                semaphore.release();
//            }
        }
    }

}
