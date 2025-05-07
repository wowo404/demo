package org.liu.thread.deepstudy.a1synchronized;

/**
 * 使用命令：javap -v SyncUsingWay.class
 */
public class A10SyncUsingWay {

    //普通方法：锁当前类new出来的对象
    public synchronized void syncMethod() {
    }

    //静态方式：锁当前class所属的对象，这个对象全局只有一个（类型，放到了方法区）
    //包括.class二进制文件都加载到了运行时数据区的方法区
    public synchronized static void syncStaticMethod() {
    }

    public void syncCodeBlock() {
        synchronized (this) {
            System.out.println("syncCodeBlock");
        }
    }

}
