package datastructures;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author lzs
 * @Date 2024/11/1 14:57
 **/
public class BlockingQueueTest {

    private static int maxThreads = 5;
    private static BlockingQueue<Integer> waitingQueue = new ArrayBlockingQueue<>(maxThreads);
    static {
        for (int i = 0; i < maxThreads; i++) {
            waitingQueue.add(i);
        }
    }

    //使用一次性拿所有的方式，等待子线程都执行完毕
    private static void clearAll() throws InterruptedException {
        for (int i = 0; i < maxThreads; i++) {
            waitingQueue.take();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程执行开始");
        for (int i = 0; i < 30; i++) {
            Integer take = waitingQueue.take();
            new Thread(() -> {
                System.out.println("线程【" + Thread.currentThread().getName() + "】拿到了【" + take + "】");
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    waitingQueue.offer(take);
                }
            }).start();
        }
        clearAll();
        System.out.println("主线程执行完毕");
    }

}
