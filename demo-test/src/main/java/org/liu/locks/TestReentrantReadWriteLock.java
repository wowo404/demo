package org.liu.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReentrantReadWriteLock {

    //读锁使用共享模式；写锁使用独占模式
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        TestReentrantReadWriteLock test = new TestReentrantReadWriteLock();
        test.testReadWriteSameTime();
    }

    public void readLock() {
        for (int i = 0; i < 5; i++) {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + ":正在进行读操作……");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":读操作完毕！");
            lock.readLock().unlock();
        }
    }

    public void testReadLock() {
        //两个线程的读操作是同时执行的，打印结果会交叉输出
        for (int i = 0; i < 2; i++) {
            new Thread(this::readLock).start();
        }
    }

    public void writeLock() {
        for (int i = 0; i < 5; i++) {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + ":正在进行write操作……");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":write操作完毕！");
            lock.writeLock().unlock();
        }
    }

    public void getReadLockWhenHoldWriteLock() {
        lock.writeLock().lock();
        for (int i = 0; i < 5; i++) {
            try {
                if (i == 3) {
                    lock.readLock().lock();//锁的降级
                    System.out.println("持有写锁期间获取读锁");
                }
                Thread.sleep(20);
                if (i == 3) {
                    System.out.println("持有写锁期间释放已获取的读锁");
                    lock.readLock().unlock();//必须释放
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":正在进行write操作……");
        }
        System.out.println(Thread.currentThread().getName() + ":write操作完毕！");
        lock.writeLock().unlock();
        lock.writeLock().lock();//这里要再次获取写锁的话，前面的所有锁必须已释放
        System.out.println(Thread.currentThread().getName() + "再次获取写锁");
        lock.writeLock().unlock();
    }

    public void getReadLockOrHoldWriteLockInDifferentThread() {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                if (finalI == 3) {
                    lock.readLock().lock();
                    System.out.println(Thread.currentThread().getName() + ":获取读锁");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":释放已获取的读锁");
                    lock.readLock().unlock();
                } else {
                    lock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName() + ":正在进行write操作……");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":write操作完毕！");
                    lock.writeLock().unlock();
                }
            }).start();
        }
    }

    public void testWriteLock() {
        //写锁是互斥的，会顺序输出，一个线程执行完另一个线程才会开始
        for (int i = 0; i < 2; i++) {
            new Thread(this::writeLock).start();
        }
    }

    public void testReadWriteSameTime() {
        //读写锁的实现必须确保写操作对读操作的内存影响。换句话说，一个获得了读锁的线程必须能看到前一个释放的写锁所更新的内容，读写锁之间为互斥
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(this::readLock);
        service.execute(this::writeLock);
    }

}
