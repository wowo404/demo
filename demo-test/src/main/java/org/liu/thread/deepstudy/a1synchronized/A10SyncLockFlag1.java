package org.liu.thread.deepstudy.a1synchronized;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 批量重偏向:A10SyncLockFlag1
 * 当我们的一个对象，object 类，在经过默认20次的争抢的情况下，会将后边的所有争抢从新偏向争抢的线程。
 * 1.当B线程争抢第 18 次的时候，触发了批量重偏向的阈值;在第十八次以及以后的争抢里，jvm,会将线程偏向线程b，因为jvm 认为，这个对象更加适合线程 B
 * <p>
 * 批量撤销:A10SyncLockFlag2
 * 如果基于批量重偏向的基础上，还在继续进行争抢达到40次，并且有第三条线程C加入了，这个时候会触发批量撤销。
 * VM 会标记该对象不能使用偏向锁，以后新创建的对象，直接以轻量级锁开始。这个时候，才是真正的完成了锁升级。
 * <p>
 * ****真正地锁升级，是依赖于 class 的，而并不是依赖于 某一个 new 出来的对象（适用于偏向锁升级为轻量级锁）****
 * ****真正地锁升级，是依赖于当前new出来的对象的（适用于轻量级锁升级为重量级锁）见A10LightLock.java****
 * <p>
 */
public class A10SyncLockFlag1 {

    static Thread A;
    static Thread B;
    static int loop = 20;

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
                for (int i = 0; i < 20; i++) {
                    Object lock = locks.get(i);
                    System.out.println("B加锁前" + i + "：" + ClassLayout.parseInstance(lock).toPrintable());
                    synchronized (lock) {
                        System.out.println("B加锁中" + i + "：" + ClassLayout.parseInstance(lock).toPrintable());
                    }
                    System.out.println("B加锁后" + i + ":" + ClassLayout.parseInstance(lock).toPrintable());
                }
                System.out.println("B新产生的对象：" + ClassLayout.parseInstance(new Object()).toPrintable());
            }
        };
        A.start();
        B.start();
    }

}
