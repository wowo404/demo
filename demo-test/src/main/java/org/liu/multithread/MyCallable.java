package org.liu.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuzhangsheng
 * @create 2019/4/10
 */
public class MyCallable implements Callable<Integer> {

    private static final AtomicInteger a = new AtomicInteger(0);

    @Override
    public Integer call() throws Exception {
        a.incrementAndGet();
        System.out.println(Thread.currentThread().getName() + "  a=" + a.get());
        return a.get();
    }
}
