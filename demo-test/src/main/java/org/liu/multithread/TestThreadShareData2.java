package org.liu.multithread;

public class TestThreadShareData2 {

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

    public void test() throws InterruptedException {
        final MyData data = new MyData();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                public void run() {
                    data.add();
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    data.dec();
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println("data.getData():" + data.getData());
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadShareData2 test = new TestThreadShareData2();
        test.test();
    }

}
