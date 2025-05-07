package org.liu.thread.deepstudy.a7threadsafequeue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Message> queue = new DelayQueue<>();
        Message message1 = new Message("a", "ok-a", 5L, TimeUnit.SECONDS);
        Message message2 = new Message("b", "ok-b", 10L, TimeUnit.SECONDS);

        queue.add(message1);
        queue.add(message2);

        while (!queue.isEmpty()) {
            Message message = queue.take();
            System.out.println(message);
        }

    }

}
