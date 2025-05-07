package org.liu.thread.deepstudy.a1synchronized;

import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * 运行此程序，查看对象头（Mark Word）
 */
public class A10SyncLockFlag {

    //当我们在启动参数中设置了开启偏向锁不延迟时，新创建的对象的mark word默认就是偏向锁的mark word。
    //只不过这个时候，因为没有线程的争抢，除了我们的锁标志位和是否为偏向锁的标志位，其他位数都是0
    static MyObject myObject = new MyObject();

    public static void main(String[] args) {
        System.out.println("================未偏向线程的偏向锁====================");
        //我这里引入的jol-core后，控制台输出的mark word只展示了16进制，没有打印2进制，why？？特别不方便看mark word
        //问题解决，引入版本不对，要使用0.9版本
        System.out.println(ClassLayout.parseInstance(myObject).toPrintable());
        //显式的调用hashcode
        System.out.println(myObject.hashCode());
        //隐式的调用hashcode
        Map<Object, String> map = new HashMap<>();
        map.put(myObject, "s");
        synchronized (myObject) {
            System.out.println("================偏向锁====================");
            System.out.println(ClassLayout.parseInstance(myObject).toPrintable());
        }
    }

    static class MyObject {
    }

}
