package org.liu.memcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;

import java.io.IOException;

public class MemcachedConfiguration {

    private String servers = "192.168.17.230:11211";

    private int poolSize = 10;

    private boolean sanitizeKeys = false;

    private boolean failureMode = true;

    private long opTimeout = 3000;

    public MemcachedClientBuilder getXMBuilder() {
        MemcachedClientBuilder memcachedClientBuilder = new XMemcachedClientBuilder(servers);
        // 开启/关闭failure模式
        memcachedClientBuilder.setFailureMode(failureMode);
        memcachedClientBuilder.setSanitizeKeys(sanitizeKeys);
        memcachedClientBuilder.setConnectionPoolSize(poolSize);
        memcachedClientBuilder.setCommandFactory(new BinaryCommandFactory());
        memcachedClientBuilder.setOpTimeout(opTimeout); // 接口操作的默认超时时间
        memcachedClientBuilder.setSessionLocator(new KetamaMemcachedSessionLocator());

        return memcachedClientBuilder;
    }

    public MemcachedClient getXMClient() {
        try {
            return getXMBuilder().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
