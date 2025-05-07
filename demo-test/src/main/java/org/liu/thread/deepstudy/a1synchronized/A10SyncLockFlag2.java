package org.liu.thread.deepstudy.a1synchronized;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class A10SyncLockFlag2 {

    static Thread A;
    static Thread B;
    static Thread C;
    static int loop = 40;

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);

        List<Object> locks = new ArrayList<Object>();
        A = new Thread() {
            public void run() {
                for (int i = 0; i < loop; i++) {
                    Object lock = new Object();
                    locks.add(lock);
                    System.out.println("A加锁前" + i + "：" + ClassLayout.parseInstance(lock).toPrintable());
                    synchronized (lock) {
                        System.out.println("A加锁中" + i + "：" + ClassLayout.parseInstance(lock).toPrintable());
                    }
                    System.out.println("A加锁后" + i + ":" + ClassLayout.parseInstance(lock).toPrintable());
                }
                System.out.println("线程A都是偏向锁");
                //执行完成后唤醒B线程
                LockSupport.unpark(B);
            }
        };
        B = new Thread() {
            public void run() {
                LockSupport.park();
                for (int i = 0; i < loop; i++) {
                    Object lock = locks.get(i);
                    System.out.println("B加锁前" + i + "：" + ClassLayout.parseInstance(lock).toPrintable());
                    synchronized (lock) {
                        System.out.println("B加锁中" + i + "：" + ClassLayout.parseInstance(lock).toPrintable());
                    }
                    System.out.println("B加锁后" + i + ":" + ClassLayout.parseInstance(lock).toPrintable());
                }
                System.out.println("B新产生的对象：" + ClassLayout.parseInstance(new Object()).toPrintable());
                LockSupport.unpark(C);
            }
        };
        C = new Thread() {
            public void run() {
                LockSupport.park();
                for (int i = 0; i < loop; i++) {
                    Object lock = locks.get(i);
                    System.out.println("C加锁前" + i + "：" + ClassLayout.parseInstance(lock).toPrintable());
                    synchronized (lock) {
                        System.out.println("C加锁中" + i + "：" + ClassLayout.parseInstance(lock).toPrintable());
                    }
                    System.out.println("C加锁后" + i + ":" + ClassLayout.parseInstance(lock).toPrintable());
                }
                System.out.println("C新产生的对象：" + ClassLayout.parseInstance(new Object()).toPrintable());
            }
        };
        A.start();
        B.start();
        C.start();

        Object lock = new Object();
        synchronized (lock) {
            System.out.println("新产生的对象：" + ClassLayout.parseInstance(lock).toPrintable());
        }
    }

}
