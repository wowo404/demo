package org.liu.multithread;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 测试自定义拒绝策略
 *
 * @author liuzhangsheng
 * @create 2019/6/4
 */
public class TestCustomRejectedInPool {

    //    private ExecutorService threadPool = new ThreadPoolExecutor(1, 2, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService threadPool = new ThreadPoolExecutor(1, 2, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), new MyRejectedExecutionHandler());
//    private ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCustomRejectedInPool test = new TestCustomRejectedInPool();
        test.testSubmit();
    }

    private void testSubmit() throws ExecutionException, InterruptedException {
        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = threadPool.submit(new MyCallable());
            list.add(future);
        }
        for (Future<Integer> future : list) {
            Integer result = future.get();
            System.out.println(Thread.currentThread().getName() + ";result=" + result);
        }
        threadPool.shutdown();
    }

    private void testInvokeAll() throws ExecutionException, InterruptedException {
        Set<MyCallable> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(new MyCallable());
        }
        List<Future<Integer>> list = threadPool.invokeAll(set);
        for (Future<Integer> future : list) {
            Integer result = future.get();
            System.out.println(Thread.currentThread().getName() + ";result=" + result);
        }
        threadPool.shutdown();
    }

    public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("queue size:" + executor.getQueue().size());
            System.out.println(Thread.currentThread().getName() + ";gooooo");
        }
    }

    /**
     * 永不失败策略
     */
    public class AlwaysNotFailurePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (executor.isShutdown()) return;
            while (executor.getQueue().remainingCapacity() != 0)
                executor.execute(r);
        }
    }

}
