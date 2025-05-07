package org.liu.thread;

import lombok.Getter;
import lombok.Setter;

/**
 * 生产者生产一个产品，通知消费者消费，如果没有产品可以消费，则消费者等待
 */
public class TestThreadCommunication {

    /**
     * 消费者
     */
    public static class Consumer implements Runnable {

        private Product product;

        public Consumer(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (product) {
                    if (!product.isFlag()) {
                        System.out.println(Thread.currentThread().getName() + "没有产品可以消费，消费者等待");
                        try {
                            product.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "消费了一个产品--" + product.getName());
                        product.setFlag(false);
                        product.notify();//通知生产者生产
                    }
                }
            }
        }
    }

    /**
     * 生产者
     * 1s生产一个产品，生产后通知消费者
     */
    public static class Producer implements Runnable {
        private Product product;

        public Producer(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            int i = 0;
            //生产产品
            while (true) {
                try {
                    synchronized (product) {
                        if (product.isFlag()) {
                            System.out.println(Thread.currentThread().getName() + "产品还没被消费，不需要生产");
                            product.wait();//当前线程等待，并释放持有的product对象锁
                        } else {
                            product.setFlag(true);
                            product.setName("product" + i);
                            System.out.println(Thread.currentThread().getName() + "生产了一个产品=" + product.getName());
                            i++;
                            Thread.sleep(1000L);//1s生产一个
                            //唤醒消费者
                            product.notify();//唤醒持有product锁的其他某一个线程

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Getter
    @Setter
    public static class Product {
        private String name;
        private boolean flag;//存在--true
    }

    public static void main(String[] args) {
        Product product = new Product();//product同时承担了锁的功能，各线程持有同一个对象锁

        Thread producerThread1 = new Thread(new Producer(product), "生产者1");
        Thread producerThread2 = new Thread(new Producer(product), "生产者2");
        Thread consumerThread1 = new Thread(new Consumer(product), "消费者1");
        Thread consumerThread2 = new Thread(new Consumer(product), "消费者2");

        producerThread1.start();
        producerThread2.start();
        consumerThread1.start();
        consumerThread2.start();
    }

}
