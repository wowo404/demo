package org.liu.memcached;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class MemcachedDemo {

    public static void main(String[] args) throws InterruptedException, MemcachedException, TimeoutException, IOException {
        get();
    }

    private static MemcachedConfiguration configuration = new MemcachedConfiguration();

    public static void add() throws InterruptedException, MemcachedException, TimeoutException, IOException {
        MemcachedClient client = configuration.getXMClient();
        //add
        boolean add = client.add("test-liu-1", 900, "ok");
        System.out.println("add to memcached, result is:" + add);

        client.shutdown();
    }

    public static void set() throws InterruptedException, MemcachedException, TimeoutException, IOException {
        MemcachedClient client = configuration.getXMClient();
        //set
        boolean set = client.set("test-liu-2", 200, 100);
        System.out.println("set to memcached, result is:" + set);

        client.shutdown();
    }

    public static void replace() throws InterruptedException, MemcachedException, TimeoutException, IOException {
        MemcachedClient client = configuration.getXMClient();
        //replace
        boolean replace = client.replace("test-liu-2", 500, "not ok");
        System.out.println("replace to memcached, result is:" + replace);

        client.shutdown();
    }

    public static void append() throws InterruptedException, MemcachedException, TimeoutException, IOException {
        MemcachedClient client = configuration.getXMClient();
        //append
        TestMemcachedPO po = new TestMemcachedPO();
        po.setId(1);
        po.setName("liu");
        po.setBirthday(new Date());
        boolean append = client.append("test-liu-2", po);
        System.out.println("append to memcached, result is :" + append);

        client.shutdown();
    }

    public static void prepend() throws InterruptedException, MemcachedException, TimeoutException, IOException {
        MemcachedClient client = configuration.getXMClient();
        //append
        TestMemcachedPO po = new TestMemcachedPO();
        po.setId(1);
        po.setName("liu");
        po.setBirthday(new Date());
        boolean append = client.prepend("test-liu-2", po, 1000);
        System.out.println("append to memcached, result is :" + append);

        Object o = client.get("test-liu-2");
        System.out.println("after prepend:" + o);

        client.shutdown();
    }

    public static void getsAndCAS() throws InterruptedException, MemcachedException, TimeoutException, IOException {
        MemcachedClient client = configuration.getXMClient();

        client.set("test-abc-1", 100, 200);

        GetsResponse<Object> gets = client.gets("test-abc-1");
        System.out.println("cas get from memcached, value is:" + gets.getValue());

        TestMemcachedPO po = new TestMemcachedPO();
        po.setId(2);
        po.setName("zhang");
        po.setBirthday(new Date());
        boolean cas = client.cas("test-abc-1", 1000, po, gets.getCas());
        System.out.println("cas operation, result is:" + cas);

        System.out.println("after cas, value is:" + client.get("test-abc-1"));

        client.shutdown();
    }

    public static void incrAndDecr() throws IOException, InterruptedException, MemcachedException, TimeoutException {
        MemcachedClient client = configuration.getXMClient();
        //incr/decr 操作的value必须是字符串形式的数字
        boolean set = client.set("test-liu-3", 900, "2000");

        long incr = client.incr("test-liu-3", 111L);
        System.out.println("value after incr:" + incr);

        long decr = client.decr("test-liu-3", 222);
        System.out.println("value after decr:" + decr);

        client.shutdown();
    }

    public static void get() throws InterruptedException, MemcachedException, TimeoutException, IOException {
        MemcachedClient client = configuration.getXMClient();
        Object o = client.get("test-liu-1");
        System.out.println("get from memcached:" + o);

        client.shutdown();
    }

    public static void delete() throws InterruptedException, MemcachedException, TimeoutException, IOException {
        MemcachedClient client = configuration.getXMClient();
        boolean delete = client.delete("test-liu-1");
        System.out.println("delete from memcached, result is:" + delete);

        client.shutdown();
    }

}
