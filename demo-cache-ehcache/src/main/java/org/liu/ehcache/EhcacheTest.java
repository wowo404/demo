package org.liu.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.MemoryUnit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 参考博文
 * https://elim.iteye.com/blog/2112170
 */
public class EhcacheTest {

    static {
        System.setProperty("net.sf.ehcache.enableShutdownHook", "true");
    }

    public void testDefault() {
        CacheManager cacheManager = new CacheManager();
        //输出当前cacheManager正在使用的配置对应的Xml格式文本
        System.out.println(cacheManager.getActiveConfigurationText());
        cacheManager.shutdown();//官方推荐调用
    }

    //其它通过不同的方式所指定的构造参数最终都会转化为一个对应的Configuration对象，然后再利用该Configuration对象初始化CacheManager
    public void testConfigByCode() {
        //新建一个缓存的配置信息
        CacheConfiguration cacheConfiguration = new CacheConfiguration().name("liu");
        //指定当前缓存的最大堆内存值为100M
        cacheConfiguration.maxBytesLocalHeap(100, MemoryUnit.MEGABYTES);

        Configuration configuration = new Configuration();
        configuration.addCache(cacheConfiguration);
        configuration.dynamicConfig(false);//不允许动态修改配置信息

        CacheManager cacheManager = new CacheManager(configuration);
        Cache cache = cacheManager.getCache("liu");
        cache.put(new Element("name", "lzs"));
        System.out.println(cache.get("liu").getObjectKey() + "--" + cache.get("liu").getObjectValue());
        cacheManager.shutdown();
    }

    public void testByInputStream() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("/ehcache.xml");
        CacheManager cacheManager = new CacheManager(is);
        is.close();
        System.out.println(cacheManager.getActiveConfigurationText());
        cacheManager.shutdown();
    }

    public void testXmlPath() {
        //这个文件路径可以是相对路径，也可以是绝对路径。这里使用的是相对路径。
        CacheManager cacheManager = new CacheManager("src/main/resources/ehcache/ehcache.xml");
        System.out.println(cacheManager.getActiveConfigurationText());
        cacheManager.shutdown();
    }

    public void testURL() {
        URL url = this.getClass().getResource("/ehcache.xml");
        CacheManager cacheManager = new CacheManager(url);
        System.out.println(cacheManager.getActiveConfigurationText());
        cacheManager.shutdown();
    }

    public void cache() {
        //内存中保存的Element的最大数量
        int maxEntriesLocalHeap = 10000;
        CacheConfiguration cacheConfiguration = new CacheConfiguration("cacheName", maxEntriesLocalHeap);
        cacheConfiguration.overflowToOffHeap(false);
        Cache cache = new Cache(cacheConfiguration);
        //使用默认配置创建CacheManager
        CacheManager cacheManager = CacheManager.create();
        //只有添加到CacheManager中的Cache才是有用的
        cacheManager.addCache(cache);
        cache.put(new Element("key", "value"));
        System.out.println(cache.get("key"));
    }

    public void testCreate() {
        //以默认配置创建一个CacheManager单例
        CacheManager cacheManager = CacheManager.create();

        //以config对应的配置创建CacheManager单例
        Configuration config = new Configuration();//以某种方式获取的Configuration对象
        cacheManager = CacheManager.create(config);

        //以configurationFileName对应的xml文件定义的配置创建CacheManager单例
        String configurationFileName = "";//xml配置文件对应的文件名称，包含路径
        cacheManager = CacheManager.create(configurationFileName);

        //以is对应的配置信息创建CacheManager单例
        InputStream is = null; //以某种方式获取到的Xml配置信息对应的输入流
        cacheManager = CacheManager.create(is);

        //以URL对应的配置信息创建CacheManager单例
        URL url = null;  //以某种方式获取到的Xml配置信息对应的URL
        cacheManager = CacheManager.create(url);
    }

//    public void test() {
//        //以默认配置创建一个CacheManager
//        CacheManager cacheManager = CacheManager.newInstance();
//
//        //以config对应的配置创建CacheManager
//        Configuration config = ...;//以某种方式获取的Configuration对象
//        cacheManager = CacheManager.newInstance(config);
//
//        //以configurationFileName对应的xml文件定义的配置创建CacheManager
//        String configurationFileName = ...;//xml配置文件对应的文件名称，包含路径
//        cacheManager = CacheManager.newInstance(configurationFileName);
//
//        //以is对应的配置信息创建CacheManager
//        InputStream is = ...; //以某种方式获取到的Xml配置信息对应的输入流
//        cacheManager = CacheManager.newInstance(is);
//
//        //以URL对应的配置信息创建CacheManager
//        URL url = ...;  //以某种方式获取到的Xml配置信息对应的URL
//        cacheManager = CacheManager.newInstance(url);
//    }

}
