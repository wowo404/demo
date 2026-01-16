package org.liu.multithread;

import lombok.Data;

public class TestObjectWait {

    @Data
    private static class Status {
        public boolean paid = false;
    }

    public static void main(String[] args) throws InterruptedException {
        Object objLock = new Object();
        Status status = new Status();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);//让订单取消线程先获取到锁
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (objLock) {
                    if (pay()) {
                        status.setPaid(true);
                        objLock.notify();
                    }
                }
            }

            private boolean pay() {
                //模拟支付，耗时1秒
                System.out.println("开始支付...");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("支付完成...");
                return true;
            }
        }.start();

        // 订单取消线程：等待30秒，未支付则取消
        new Thread() {
            @Override
            public void run() {
                synchronized (objLock) {
                    try {
                        // 关键：用while循环，防止虚假唤醒后误判状态
                        while (!status.isPaid()) {
                            // 等待30秒：30秒内没被唤醒（支付成功），则超时
                            System.out.println("订单取消线程，等待支付完成...");
                            objLock.wait(30000);
                        }
                        System.out.println("订单支付成功，无需取消");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("取消任务被中断（如用户手动取消）");
                    }
                }
            }

            private void cancelOrder() {
                System.out.println("订单取消成功");
            }
        }.start();

        Thread.sleep(3000L);
    }

}
