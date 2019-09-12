package org.liu.byteserver;

import org.quickserver.net.AppException;
import org.quickserver.net.server.DataMode;
import org.quickserver.net.server.DataType;
import org.quickserver.net.server.QuickServer;
import org.quickserver.util.xmlreader.*;

public class BinaryServer {

    public static void main(String[] args) throws AppException {
        QuickServerConfig config = new QuickServerConfig();
        config.setPort(4123);
        config.setName("Byte Server v1");
        config.setClientEventHandler(new BinaryEventHandler());
        config.setClientCommandHandler(new BinaryCommandHandler());
        config.setClientBinaryHandler(new BinaryHandler());

        //WARNING:这里设置为BINARY，在commandHandler的gotConnected中只能调用sendClientBinary
        //并且要实现ClientBinaryHandler接口，不然会报NPE
        DefaultDataMode defaultDataMode = new DefaultDataMode();
        defaultDataMode.setDataMode(DataMode.BINARY, DataType.IN);
        defaultDataMode.setDataMode(DataMode.BINARY, DataType.OUT);

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
