package org.liu.google.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.Executors;

/**
 * guava RateLimit
 *
 * @author liuzhangsheng
 * @create 2018/6/11
 */
public class TestRateLimit {

    public static void main(String[] args) {

        testRateLimiter();

    }


    /**
     * RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数
     */

    public static void testRateLimiter() {

        ListeningExecutorService executorService = MoreExecutors

                .listeningDecorator(Executors.newCachedThreadPool());

        RateLimiter limiter = RateLimiter.create(5.0); // 每秒不超过4个任务被提交

        for (int i = 0; i < 10; i++) {

            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞

            final ListenableFuture<Integer> listenableFuture = executorService

                    .submit(new AsyncTask("is " + i));

        }
    }

}
