package org.liu.thread.deepstudy.a0thread;

public class A7ThreadSleepReleaseCPU {

    public static void main(String[] args) {
        //运行此方法，然后看任务管理器里CPU的负载图
        for (int i = 0; i < 100; i++) {
            new Thread(new SubThread(), "DaemonThread" + i).start();
        }
    }

    static class SubThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("FINISHED");
            }
        }
    }

}
