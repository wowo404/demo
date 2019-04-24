package org.liu.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liuzhangsheng
 * @create 2019/4/10
 */
public class TestMyCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
//        futureTask.run();//这是一种运行方法
        Thread t1 = new Thread(futureTask);
        Thread t2 = new Thread(futureTask);
        Thread t3 = new Thread(futureTask);

        //WARN：即使new多个thread，但只会执行一次task
        t1.start();
        System.out.println("isCancelled:" + futureTask.isCancelled());
        System.out.println("isDone:" + futureTask.isDone());
        Integer result = futureTask.get();//此方法会一直阻塞主线程，直到获取到task的结果
        System.out.println(result);

        t2.start();
        System.out.println("isCancelled:" + futureTask.isCancelled());
        System.out.println("isDone:" + futureTask.isDone());
        System.out.println(futureTask.get());

        t3.start();
        System.out.println("isCancelled:" + futureTask.isCancelled());
        System.out.println("isDone:" + futureTask.isDone());
        System.out.println(futureTask.get());

    }

}
