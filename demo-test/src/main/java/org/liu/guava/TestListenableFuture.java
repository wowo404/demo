package org.liu.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class TestListenableFuture {

    public static void main(String[] args) {
        testListenableFuture();
    }

    public static void testListenableFuture() {
        ListeningExecutorService executorService = MoreExecutors
                .listeningDecorator(Executors.newCachedThreadPool());

        final ListenableFuture<Integer> listenableFuture = executorService
                .submit(new AsyncTask("testListenableFuture"));

        //同步获取调用结果
        try {
            System.out.println(listenableFuture.get());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }

        //第一种方式
        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("get listenable future's result " + listenableFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, executorService);

        //第二种方式
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("get listenable future's result with callback " + result);
            }
            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
