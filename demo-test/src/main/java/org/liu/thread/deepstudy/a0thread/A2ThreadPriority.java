package org.liu.thread.deepstudy.a0thread;

public class A2ThreadPriority {

    public static void main(String[] args) {
        MyThread thread1 = new MyThread("t1");
        MyThread thread2 = new MyThread("t2");
        thread1.setPriority(2);
        thread2.setPriority(5);//setPriority不能保证执行顺序，只会指定当前线程执行的cpu时间片
        thread1.start();
        thread2.start();
    }

    static class MyThread extends Thread {

        private String threadName;

        MyThread(String threadName) {
            super(threadName);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "(" + Thread.currentThread().getPriority() + ")" + i);
            }
        }
    }

}
