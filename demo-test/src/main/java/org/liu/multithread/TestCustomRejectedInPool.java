package org.liu.multithread;

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
        test.test();
    }

    private void test() throws ExecutionException, InterruptedException {
        Set<MyCallable> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(new MyCallable(i));
        }
        List<Future<String>> list = threadPool.invokeAll(set);
        for (Future<String> future : list) {
            String result = future.get();
            System.out.println(Thread.currentThread().getName() + ";result=" + result);
        }
        threadPool.shutdown();
    }

    public class MyRejectedExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//            FutureTask<Void> futureTask = new FutureTask<>(r, null);
//            futureTask.run();

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

    public static class MyCallable implements Callable<String> {

        static {
            //TODO:为什么在这里改变name是无效的
            Thread.currentThread().setName("myCallable-" + System.currentTimeMillis());
        }

        private int index;
        public MyCallable(int index){
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String call() throws Exception {
            Thread.currentThread().setName("from my callable-" + System.currentTimeMillis());
            Thread.sleep(2000);
            String result = Thread.currentThread().getName() + ";index=" + index;
            System.out.println(result);
            return result;
        }
    }

}
