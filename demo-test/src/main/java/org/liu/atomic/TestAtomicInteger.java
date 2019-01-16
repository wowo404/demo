package org.liu.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuzhangsheng
 * @create 2018/12/17
 */
public class TestAtomicInteger {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
    }


}
