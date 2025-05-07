package org.liu.thread.deepstudy.a4lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCondition<T> {

    private Object[] items;
    //添加的下标，删除的下标，数组的当前数量
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();//消费者端的condition
    private Condition notFull = lock.newCondition();//生产者端的condition
    //每创建一个condition，就会创建一个等待队列
    //是不是我们的阻塞队列都是如此实现的呢? 后续我们会对队列进行一个分析，也会看源码，到时候你就能看到
    //队列的头和队列的尾都是分别创建了一个condition，就是为了将我们的队列的双端的等待队列进行区分，互不影响。

    public LockCondition(int size) {
        items = new Object[size];
    }

    //添加一个元素，如果数组满，则添加线程进入等待状态，直到有"空位"
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {//如果生产的产品已满
                notFull.await();//则通知生产者线程暂停生产，进入等待队列
            }
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();//通知消费者线程进行消费
        } finally {
            lock.unlock();
        }
    }

    //从头部删除一个元素，如果数组为空，则删除线程进入等待状态，直到有新添加元素
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {//没有产品可以消费了
                notEmpty.await();//通知消费者线程暂停消费，进入等待队列
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal();//通知生产者线程进行生产
            return (T) x;
        } finally {
            lock.unlock();
        }
    }

}
