package org.liu.multithread;

import java.util.concurrent.Callable;

/**
 * @author liuzhangsheng
 * @create 2019/4/10
 */
public class MyCallable implements Callable<Integer> {

    private int a = 0;

    @Override
    public Integer call() throws Exception {
        ++a;
        System.out.println(Thread.currentThread().getName() + "  a=" + a);
        return a;
    }
}
