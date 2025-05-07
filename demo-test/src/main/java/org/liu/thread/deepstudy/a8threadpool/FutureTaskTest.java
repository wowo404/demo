package org.liu.thread.deepstudy.a8threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask();
        //运行方式一
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 2, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(20));
//        Future<String> future = executor.submit(myTask);
//        System.out.println(future.get());
//        executor.shutdown();
        //运行方式二
        FutureTask<String> futureTask = new FutureTask<>(myTask);
        futureTask.run();
        System.out.println(futureTask.get());
    }

    public static class MyTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "Hello World!";
        }
    }

}
