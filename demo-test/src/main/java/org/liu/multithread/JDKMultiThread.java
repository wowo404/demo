package org.liu.multithread;

import java.util.concurrent.*;

/**
 * 用jdk的api实现多线程操作
 * @author liuzhangsheng
 * @create 2019/2/18
 */
public class JDKMultiThread {

    private ExecutorService threadPool = Executors.newFixedThreadPool(6);

    public void test() throws ExecutionException, InterruptedException {
        Callable<String> t = new Callable<String>() {
            @Override
            public String call() {
                synchronized (this) {
                    return doSomething("from:" + Thread.currentThread().getName());
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Future<String> f = threadPool.submit(t);
            String result = f.get();
            System.out.println("circle " + i + " result is:" + result);
        }
        threadPool.shutdown();
    }

    private String doSomething(String msg){
        System.out.println(msg);
        return msg;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        JDKMultiThread thread = new JDKMultiThread();
        thread.test();
    }

}
