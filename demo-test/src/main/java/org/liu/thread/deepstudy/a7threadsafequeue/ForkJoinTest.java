package org.liu.thread.deepstudy.a7threadsafequeue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;
    private Integer start;
    private Integer end;

    public ForkJoinTest(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {//拆分任务，获取结果
        System.out.println(Thread.currentThread().getName() + ": start " + start + " end " + end);
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        //如果任务足够小，就直接进行计算
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            //如果任务量大于阈值，则分裂
            int middle = (end + start) / 2;
            ForkJoinTest leftTask = new ForkJoinTest(start, middle);
            ForkJoinTest rightTask = new ForkJoinTest(middle + 1, end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //获取子任务的结果
            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTest forkJoinTest = new ForkJoinTest(1, 10);
        //执行任务
        ForkJoinTask<Integer> submit = forkJoinPool.submit(forkJoinTest);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
