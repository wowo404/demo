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
                        System.out.println("没有产品可以消费，消费者等待");
                        try {
                            product.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("我已经消费了一个产品--" + product.getName());
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
                            System.out.println("产品还没被消费，不需要生产");
                            product.wait();//当前线程等待，并释放持有的product对象锁
                        } else {
                            product.setFlag(true);
                            product.setName("product" + i);
                            System.out.println("生产了一个产品=" + product.getName());
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
        Product product = new Product();//product同时承担了锁的功能

        Thread producerThread = new Thread(new Producer(product));
        Thread consumerThread = new Thread(new Consumer(product));

        producerThread.start();
        consumerThread.start();
    }

}
