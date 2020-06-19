package org.liu.multithread;

public class TestThreadLocal {

    private ThreadLocal<Integer> localData = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        TestThreadLocal local = new TestThreadLocal();
        local.test();
    }

    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(){
                @Override
                public void run() {
                    localData.set(finalI);
                }
            }.start();
        }
        Thread.sleep(1000L);
        System.out.println(localData.get());
    }

}
