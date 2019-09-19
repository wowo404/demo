package org.liu.multithread;

public class TestSync {

    private int var = 0;

    public static void main(String[] args) {
        TestSync test = new TestSync();
        test.test1();
    }

    public void test(){
        for(int i = 0; i < 50; i++){
            new Thread(() -> {
                synchronized (this) {
                    var++;
                    System.out.println(var);
                }
            }).start();
        }
    }

    public void test1(){
        for(int i = 0; i < 50; i++){
            new Thread(() -> {
                var++;
                System.out.println(var);
            }).start();
        }
    }

    public void add(){
        var++;
    }

}
