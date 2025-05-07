package org.liu.thread.deepstudy.a8threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSimpleTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10,
                200, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 20; i++) {
            //WARNING：一定要自定义拒绝策略，不要使用默认的AbortPolicy，
            // 不然的话当任务数量达到maximumPoolSize+capacity后，子线程抛出异常，主线程会一直挂起
            //如果是在生产环境，造成的后果就是有一条线程一直挂着（结合以前的经验我猜测是这样，还需要具体在web服务环境中进行测试）
            executor.execute(new MyTask(i));
//            executor.submit(new MyTask(i));
            System.out.println("线程池中的线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" + executor.getQueue().size()
                    + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
        }
        System.out.println("线程池中当前需要执行的任务数量：" + executor.getTaskCount());
        System.out.println("线程池中当前正在活动的任务数量：" + executor.getActiveCount());
        //主线程休眠一会儿，等子线程全部执行完毕
        Thread.sleep(5000);
        System.out.println("本次运行批量任务，触及到的最大的线程池数量：" + executor.getLargestPoolSize());

        executor.shutdown();
    }

    public static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (r instanceof MyTask) {
                MyTask task = (MyTask) r;
                System.out.println("记录日志中、存入数据库、发送消息等操作手段：" + task.taskNumber);
            }
        }
    }

    public static class MyTask implements Runnable {

        private int taskNumber;

        public MyTask(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": " + taskNumber);
        }
    }

}
