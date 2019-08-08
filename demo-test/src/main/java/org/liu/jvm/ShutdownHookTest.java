package org.liu.jvm;

public class ShutdownHookTest {
    public static void main(String[] args) {
        run();
    }

    private static void run(){
        System.out.println("1: Main start");

        Thread mainThread = Thread.currentThread();

        //注册一个 ShutdownHook
        ShutdownSampleHook thread = new ShutdownSampleHook(mainThread);
        Runtime.getRuntime().addShutdownHook(thread);

        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            System.out.println("3: mainThread get interrupt signal.");
        }

        System.out.println("4: Main end");
    }

}

class ShutdownSampleHook extends Thread {
    private Thread mainThread;

    @Override
    public void run() {
        System.out.println("2: Shut down signal received.");
        mainThread.interrupt();//给主线程发送一个中断信号
        try {
            mainThread.join(); //等待 mainThread 正常运行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5: Shut down complete.");
    }

    public ShutdownSampleHook(Thread mainThread) {
        this.mainThread = mainThread;

    }
}