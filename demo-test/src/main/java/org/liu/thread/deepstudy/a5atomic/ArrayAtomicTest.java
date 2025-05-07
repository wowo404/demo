package org.liu.thread.deepstudy.a5atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class ArrayAtomicTest {

    static int[] arr = new int[10];

    public static void main(String[] args) throws InterruptedException {
        arr[0] = 1;
        arr[1] = 2;
        AtomicIntegerArray aia = new AtomicIntegerArray(arr);

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                aia.compareAndSet(0, 1, 11);
            }
        }, "threadA");
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                aia.compareAndSet(0, 11, 111);
            }
        }, "threadB");

        threadA.start();
        threadB.start();

        Thread.sleep(1000);
        System.out.println("原始数组中的值：" + arr[0]);
        System.out.println("原子更新数组中的值：" + aia.get(0));

    }

}
