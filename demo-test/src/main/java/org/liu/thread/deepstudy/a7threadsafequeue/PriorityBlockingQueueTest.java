package org.liu.thread.deepstudy.a7threadsafequeue;

import java.util.Arrays;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue内部使用数组表示堆结构
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(Arrays.asList(1, 4, 6, 10));
        queue.offer(3);
//        queue.forEach(System.out::println);//forEach不保证按·优先级·顺序输出
        //正确的输出方式如下
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

}
