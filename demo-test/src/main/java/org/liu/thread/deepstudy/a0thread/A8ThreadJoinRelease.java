package org.liu.thread.deepstudy.a0thread;

public class A8ThreadJoinRelease {

    static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(new SubThread(), "Thread-" + i);
            t.start();
            Thread.sleep(100);
        }
    }

    static class SubThread implements Runnable {
        @Override
        public void run() {
            //如果更换为synchronized(Thread.currentThread())，则两条线程都会打印出来
            //synchronized (obj)，这种写法只会打印Thread-0
            synchronized (obj) {
                System.out.println("获取到锁！ThreadName:" + Thread.currentThread().getName());
                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
