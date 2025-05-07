package org.liu.commonspool2;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 教程：https://cloud.tencent.com/developer/article/2398623
 */
public class TestCommonsPool2 {

    public static void main(String[] args) {
        //此示例代码并没有很好的展示对象池技术，更好的示例应该是模拟多线程的环境，且ObjectPool是多线程共享的，要用到borrowObject和returnObject
        GenericObjectPoolConfig<MyConnection> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(5);
        poolConfig.setMinIdle(0);
        try (ObjectPool<MyConnection> pool = new GenericObjectPool<>(new MyConnectionPooledObjectFactory())) {
            MyConnection myConnection = pool.borrowObject();
            System.out.println(myConnection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(poolConfig);
    }

}
