package org.liu.thread.deepstudy.a1synchronized;

import org.openjdk.jol.info.ClassLayout;

/**
 * 偏问锁:
 * 1.A线程获取偏向锁，并且A线程死亡退出。B线程争抢偏向锁，会直接升级当前对象的锁为轻量级锁。这只是针对我们争抢了一次。A10SyncSyncLockRelease类演示
 * 2.A线程获取偏向锁，并且A线程没有释放偏向锁()，还在 sync,的代码块里边。B线程此时过来争抢偏向锁，会直接升级为重量级锁。
 * 3.A线程获取偏向锁，并且A线程释放了锁，但是A线程并没有死亡还在活跃状态。B 线程过来争抢，会直接升级为轻量级锁。
 * 综上所述，
 * 当我们尝试第一次竞争偏向锁时，如果A线程已经死亡，升级为轻量级锁:
 * 如果A线程未死亡，并且未释放锁，直接升级为重量级锁;
 * 如果A线程未死亡，并且已经释放了锁，直接升级为轻量级锁。
 */
public class A10SyncSyncLockRelease1 {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        System.out.println("A加锁前：" + ClassLayout.parseInstance(lock).toPrintable());
        Thread A = new Thread() {
            public void run() {
                synchronized (lock) {
                    System.out.println("A加锁中：" + ClassLayout.parseInstance(lock).toPrintable());
                    try {
                        //第一次运行此代码放在sync里面，会产生类注释上的第2点
                        // 第二次运行把此代码移动到sync外面，会产生类注释上的第3点
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        A.start();
        Thread.sleep(500);
        System.out.println("B加锁前：" + ClassLayout.parseInstance(lock).toPrintable());
        Thread B = new Thread() {
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
        B.start();
    }

}
