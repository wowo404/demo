package org.liu.thread;

public class TestThread {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new RunA());
        Thread threadB = new Thread(new RunB());
        Thread threadC = new Thread(new RunC());

        threadA.start();
        threadB.start();
        //如果不调用join方法，线程ABC的输出基本上是乱序输出
        //调用了join方法，线程AB的输出继续执行，基本上是乱序输出，表示执行完线程AB后再执行之后的代码，相当于主线程main阻塞
        threadB.join();
        threadC.start();

        System.out.println("finish");
    }

    static class RunA implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("a" + i);
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class RunB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("b" + i);
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class RunC implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("c" + i);
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
