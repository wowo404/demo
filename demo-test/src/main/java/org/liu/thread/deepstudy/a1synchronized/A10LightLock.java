package org.liu.thread.deepstudy.a1synchronized;

import org.openjdk.jol.info.ClassLayout;

/**
 * 运行此程序要添加jvm运行参数：-XX:BiasedLockingStartupDelay=10000000 或 -XX:-UseBiasedLocking 关闭偏向锁
 * <p>
 * 轻量级锁升级为重量级锁：这个时候，只要我们的线程发生了竞争，并且CAS替换失败，就会发起膨胀，升级为重量级锁（针对的是一个对象实例）
 * <p>
 * 详细解释：
 * 轻量级锁--重量级锁: 释放锁(前四步)并唤醒等待线程
 * 1.线程1 初始化 monitor 对象;
 * 2.将状态设置为膨胀中(inflating):
 * 3.将 monitor 里边的 header 属性，set为对象的 mark word;(将自己lock record（mark word的hashcode,分代年龄,是否为偏向锁） set 到 object monitor 对象的 header属性里)
 * 4.设置对象头为重量级锁状态(标记位改为00);然后将前 30 位指向第1步他初始化的monitor 对象;(真正地锁升级是由线程1操控的)
 * 5.唤醒线程 2;
 * 6.线程2开始争抢重量级锁。(线程2就干了一件事儿，就是弄了一个临时的重量级锁指针吧?还不是最后的重量级锁指针。因为最后的重量级锁指针是线程1初始化的并且是线程1修改的。
 * 而且，线程2被唤醒之后，还不一定能够抢到这个重量级锁。sync 是非公平锁线程2费力不讨好，但是线程2做了一件伟大的事情:他是锁升级的基者。)
 */
public class A10LightLock {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        System.out.println("A加锁前：" + ClassLayout.parseInstance(lock).toPrintable());
        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("A加锁中：" + ClassLayout.parseInstance(lock).toPrintable());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t1.start();
        Thread.sleep(500);
        System.out.println("B加锁前：" + ClassLayout.parseInstance(lock).toPrintable());
        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("B加锁中：" + ClassLayout.parseInstance(lock).toPrintable());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t2.start();

        Thread.sleep(5000);
        synchronized (lock) {
            System.out.println("再次加锁中：" + ClassLayout.parseInstance(lock).toPrintable());
        }

        Object newObj = new Object();
        synchronized (newObj) {
            System.out.println("新对象加锁中：" + ClassLayout.parseInstance(newObj).toPrintable());
        }
    }

}
