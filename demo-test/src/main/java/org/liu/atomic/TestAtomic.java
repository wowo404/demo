package org.liu.atomic;

import org.liu.json.BaseReq;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liuzhangsheng
 * @create 2018/12/17
 */
public class TestAtomic {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());

        AtomicReference<BaseReq> reference = new AtomicReference<>(null);
        System.out.println(reference.get());

    }


}
