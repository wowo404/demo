package org.liu.thread.deepstudy.a1synchronized;

public class A10ThreadReIn implements Runnable {

    static A10ThreadReIn instance = new A10ThreadReIn();
    static int i = 0;
    static int j = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("i = " + i);
        System.out.println("j = " + j);
    }

    @Override
    public void run() {
        for (int k = 0; k < 100000; k++) {
            synchronized (this) {
                i++;
                increment();
            }
        }
    }

    public synchronized void increment() {
        j++;
    }
}
