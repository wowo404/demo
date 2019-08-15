package org.liu.echoserver;

import org.quickserver.net.AppException;
import org.quickserver.net.server.DataMode;
import org.quickserver.net.server.DataType;
import org.quickserver.net.server.QuickServer;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class EchoServer {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String cmdHandler = "org.liu.echoserver.EchoCommandHandler";
        String authenticator = "org.liu.echoserver.EchoServerQuickAuthenticator";
        String authenticationHandler = "org.liu.echoserver.EchoClientAuthenticationHandler";
        String data = "org.liu.echoserver.EchoServerPoolableData";
        String commandPlugin = "org.liu.echoserver.qsadmin.QSAdminCommandPlugin";
        String binaryHandler = "org.liu.echoserver.EchoClientBinaryHandler";
        String clientExtendedEventHandler = "org.liu.echoserver.EchoClientExtendedEventHandler";

        QuickServer quickServer = new QuickServer(cmdHandler);

        quickServer.setPort(4123);
        quickServer.setName("Echo Server v1.0");
        //setClientAuthenticationHandler和setAuthenticator同时存在时，setClientAuthenticationHandler起效
//        quickServer.setAuthenticator(authenticator);
        quickServer.setClientAuthenticationHandler(authenticationHandler);
        quickServer.setClientData(data);
        quickServer.setClientBinaryHandler(binaryHandler);
        quickServer.setClientExtendedEventHandler(clientExtendedEventHandler);
        quickServer.setTimeout(60000);

        //日志配置
        File log = new File("./log/");
        if (!log.exists()) {
            log.mkdirs();
        }
        //分离QuickServer的日志和应用的日志
        Logger logger = Logger.getLogger("");//QuickServer的日志
        logger.setLevel(Level.FINEST);
        try {
            FileHandler xmlLog = new FileHandler("log/EchoServer.xml");
            logger.addHandler(xmlLog);

            logger = Logger.getLogger("echoserver");//应用日志
            logger.setLevel(Level.INFO);
            FileHandler txtLog = new FileHandler("log/EchoServer.txt");
            txtLog.setFormatter(new SimpleFormatter());
            logger.addHandler(txtLog);
            quickServer.setAppLogger(logger);//设置应用的日志

//            quickServer.setDefaultDataMode(DataMode.BINARY, DataType.IN);
//            quickServer.setDefaultDataMode(DataMode.BINARY, DataType.OUT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        quickServer.setConsoleLoggingLevel(Level.FINEST);

        //设置一些数据让QSAdminServer可以修改
        Object[] store = new Object[]{"12.00"};
        quickServer.setStoreObjects(store);

        //设置管理QuickServer的server
        quickServer.setQSAdminServerPort(4124);
        quickServer.getQSAdminServer().getServer().setName("EchoAdminServer v1.0");
        quickServer.getQSAdminServer().getServer().setLoggingLevel(Level.FINEST);

        try {
            quickServer.getQSAdminServer().setCommandPlugin(commandPlugin);
            //admin server的用户名密码：Admin/QsAdm1n
            quickServer.startQSAdminServer();
            quickServer.startServer();
        } catch (AppException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
