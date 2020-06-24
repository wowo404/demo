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
        lock.readLock().lock();
        System.out.println("start time:" + System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":正在进行读操作……");
        }
        System.out.println(Thread.currentThread().getName() + ":读操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
        lock.readLock().unlock();
    }

    public void testReadLock() {
        //两个线程的读操作是同时执行的，打印结果会交叉输出
        for (int i = 0; i < 2; i++) {
            new Thread(this::readLock).start();
        }
    }

    public void writeLock() {
        lock.writeLock().lock();
        System.out.println("start time:" + System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":正在进行write操作……");
        }
        System.out.println(Thread.currentThread().getName() + ":write操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
        lock.writeLock().unlock();
    }

    public void testWriteLock() {
        //写锁是互斥的，会顺序输出，一个线程执行完另一个线程才会开始
        for (int i = 0; i < 2; i++) {
            new Thread(this::writeLock).start();
        }
    }

    public void testReadWriteSameTime(){
        //读写锁的实现必须确保写操作对读操作的内存影响。换句话说，一个获得了读锁的线程必须能看到前一个释放的写锁所更新的内容，读写锁之间为互斥
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(this::readLock);
        service.execute(this::writeLock);
    }

}
