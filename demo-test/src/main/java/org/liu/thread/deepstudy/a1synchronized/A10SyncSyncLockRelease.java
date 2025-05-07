package org.liu.thread.deepstudy.a1synchronized;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 运行此代码，查看mark word，重点看两个标识位：是否偏向锁，锁类型标志位
 */
public class A10SyncSyncLockRelease {

    static Thread A;
    static Thread B;

    public static void main(String[] args) {
        final List<Object> list = new ArrayList<Object>();
        A = new Thread() {
            public void run() {
                Object lock = new Object();
                list.add(lock);
                System.out.println("A加锁前：" + ClassLayout.parseInstance(lock).toPrintable());
                synchronized (lock) {
                    System.out.println("A加锁中：" + ClassLayout.parseInstance(lock).toPrintable());
                }
                System.out.println("A加锁后：" + ClassLayout.parseInstance(lock).toPrintable());
                //执行完此代码后，A线程死亡退出
                LockSupport.unpark(B);
            }
        };
        B = new Thread() {
            public void run() {
                LockSupport.park();
                Object lock = list.get(0);
                System.out.println("B加锁前：" + ClassLayout.parseInstance(lock).toPrintable());
                synchronized (lock) {
                    System.out.println("B加锁中：" + ClassLayout.parseInstance(lock).toPrintable());
                }
                System.out.println("B加锁后：" + ClassLayout.parseInstance(lock).toPrintable());
                System.out.println("新对象产生：" + ClassLayout.parseInstance(new Object()).toPrintable());
            }
        };
        A.start();
        B.start();
    }

}
