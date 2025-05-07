package org.liu.thread.deepstudy.a6concurrentclass;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

    static Exchanger<String> ex = new Exchanger<>();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            String change = "a";
            try {
                String fromB = ex.exchange(change);
                System.out.println("我是" + Thread.currentThread().getName() + "，我用a从线程B中交换到了：" + fromB);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "threadA");
        Thread threadB = new Thread(() -> {
            String change = "b";
            try {
                String fromA = ex.exchange(change);
                System.out.println("我是" + Thread.currentThread().getName() + "，我用b从线程A中交换到了：" + fromA);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "threadB");
        threadA.start();
        threadB.start();
    }

}
