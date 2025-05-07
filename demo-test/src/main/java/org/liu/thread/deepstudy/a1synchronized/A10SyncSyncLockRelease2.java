package org.liu.thread.deepstudy.a1synchronized;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 4.A线程获取偏向锁，并且A线程没有释放偏向锁()，还在 sync 的代码块里边。B 线程多次争抢锁，会在加锁过程中采用重量级锁;但是，一旦锁被释放，当前对象还是会以轻量
 * 级锁的初始状态执行。这块算是锁降级吗?不算。这个示例就是我们一些博客论坛里边的一些认为可以锁降级的示例。
 * --- 锁升级是在线程运行过程中和争抢过程中的一种升级。这句话里一定要注意 中 这个字儿,很重要。我想请问,刚才我们演示的是在竞争中的锁降级吗?
 * 5.A线程获取偏向锁，并且A线程释放了锁，但是A线程并没有死亡还在活跃状态。B线程过来争抢。部分争抢会升级为轻量级锁:部分争抢会依旧保持偏向锁。
 */
public class A10SyncSyncLockRelease2 {

    public static void main(String[] args) throws InterruptedException {
        List<Object> locks = new ArrayList<Object>();
        for (int i = 0; i < 20; i++) {
            Object lock = new Object();
            locks.add(lock);
            System.out.println("A加锁前" + i + "：" + ClassLayout.parseInstance(lock).toPrintable());
            Thread A = new Thread() {
                public void run() {
                    synchronized (lock) {
                        System.out.println("A加锁中：" + ClassLayout.parseInstance(lock).toPrintable());
                        try {
                            //此代码放在sync里面，演示的是类注释中的第4点
                            //此代码移动到sync外面，演示的是类注释中的第5点
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
            A.start();
        }

        Thread.sleep(200);

        for (int i = 0; i < 20; i++) {
            Object lock = locks.get(i);
            System.out.println("B加锁前" + i + "：" + ClassLayout.parseInstance(lock).toPrintable());
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

        Thread.sleep(5000);
        Object nineteen = locks.get(19);
        synchronized (nineteen) {
            System.out.println("再次加锁：" + ClassLayout.parseInstance(nineteen).toPrintable());
        }
    }

}
