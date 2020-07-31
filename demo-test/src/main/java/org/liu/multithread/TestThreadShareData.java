package org.liu.multithread;

public class TestThreadShareData {

    public class MyData {

        private int j = 0;

        public synchronized void add() {
            j++;
            System.out.println("线程" + Thread.currentThread().getName() + "j 为：" + j);
        }

        public synchronized void dec() {
            j--;
            System.out.println("线程" + Thread.currentThread().getName() + "j 为：" + j);
        }

        public int getData() {
            return j;
        }
    }

    public class AddRunnable implements Runnable {

        MyData data;

        public AddRunnable(MyData data) {
            this.data = data;
        }

        public void run() {
            data.add();
        }
    }

    public class DecRunnable implements Runnable {

        MyData data;

        public DecRunnable(MyData data) {
            this.data = data;
        }

        public void run() {
            data.dec();
        }
    }

    public void test() {
        MyData data = new MyData();

        Runnable add = new AddRunnable(data);
        Runnable dec = new DecRunnable(data);

        for (int i = 0; i < 2; i++) {
            new Thread(add).start();
            new Thread(dec).start();
        }
    }

    public static void main(String[] args) {
        TestThreadShareData test = new TestThreadShareData();
        test.test();
    }

}
