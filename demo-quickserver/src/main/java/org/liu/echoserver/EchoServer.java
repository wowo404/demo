package org.liu.echoserver;

import org.quickserver.net.AppException;
import org.quickserver.net.server.QuickServer;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class EchoServer {

    public static void main(String[] args) {
        String cmdHandler = "org.liu.echoserver.EchoCommandHandler";
        String authenticator = "org.liu.echoserver.EchoServerQuickAuthenticator";
        String data = "org.liu.echoserver.EchoServerPoolableData";
        String commandPlugin = "org.liu.echoserver.qsadmin.QSAdminCommandPlugin";
        String binaryHandler = "org.liu.echoserver.EchoClientBinaryHandler";

        QuickServer quickServer = new QuickServer(cmdHandler);

        quickServer.setPort(4123);
        quickServer.setName("Echo Server v1.0");
        quickServer.setAuthenticator(authenticator);
        quickServer.setClientData(data);
        quickServer.setClientBinaryHandler(binaryHandler);

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
