package org.liu.objectserver;

import org.quickserver.net.AppException;
import org.quickserver.net.server.DataMode;
import org.quickserver.net.server.DataType;
import org.quickserver.net.server.QuickServer;
import org.quickserver.util.xmlreader.*;

public class ObjectServer {

    public static void main(String[] args) throws AppException {
        QuickServerConfig config = new QuickServerConfig();
        config.setPort(4123);
        config.setName("Object Server v1");
        config.setClientEventHandler(new ObjectEventHandler());
        config.setClientCommandHandler(new ObjectCommandHandler());
        config.setClientObjectHandler(new ObjectHandler());//Object

        DefaultDataMode defaultDataMode = new DefaultDataMode();
        //object模式真是垃圾。。。这里设置会导致整个服务连接都连不上来
//        defaultDataMode.setDataMode(DataMode.OBJECT, DataType.IN);
//        defaultDataMode.setDataMode(DataMode.OBJECT, DataType.OUT);

        config.setDefaultDataMode(defaultDataMode);
        config.setConsoleLoggingLevel("FINEST");
        config.setConsoleLoggingFormatter("org.quickserver.util.logging.SimpleConsoleFormatter");
        config.setCommunicationLogging(true);

        InitServerHooks initServerHooks = new InitServerHooks();
        initServerHooks.addClassName("org.quickserver.util.logging.SimpleJDKLoggingHook");

        config.setInitServerHooks(initServerHooks);

        ObjectPoolConfig objectPoolConfig = new ObjectPoolConfig();
        objectPoolConfig.setMaxIdle(20);
        objectPoolConfig.setMaxActive(-1);

        ThreadObjectPoolConfig threadObjectPoolConfig = new ThreadObjectPoolConfig();
        threadObjectPoolConfig.setInitSize(20);
        threadObjectPoolConfig.setMaxActive(50);
        threadObjectPoolConfig.setMaxIdle(10);

        objectPoolConfig.setThreadObjectPoolConfig(threadObjectPoolConfig);

        config.setObjectPoolConfig(objectPoolConfig);

        AdvancedSettings advancedSettings = new AdvancedSettings();
        advancedSettings.setCharset("UTF-8");
        advancedSettings.setBacklog(1024);
        advancedSettings.setSocketLinger(-1);

        config.setAdvancedSettings(advancedSettings);

        QuickServer myServer = new QuickServer();
        if (myServer.initService(config)) {
            myServer.startServer();
        }

    }

}
