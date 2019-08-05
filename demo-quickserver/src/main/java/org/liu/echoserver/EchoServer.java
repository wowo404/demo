package org.liu.echoserver;

import org.quickserver.net.AppException;
import org.quickserver.net.server.QuickServer;

public class EchoServer {

    public static void main(String[] args) {
        String cmdHandler = "org.liu.echoserver.EchoCommandHandler";
        String authenticator = "org.liu.echoserver.EchoServerQuickAuthenticator";
        String data = "org.liu.echoserver.EchoServerPoolableData";
        String commandPlugin = "org.liu.echoserver.qsadmin.QSAdminCommandPlugin";

        QuickServer quickServer = new QuickServer(cmdHandler);
        quickServer.setPort(4123);
        quickServer.setName("Echo Server v1.0");
        quickServer.setAuthenticator(authenticator);
        quickServer.setClientData(data);

        //设置一些数据让QSAdminServer可以修改
        Object[] store = new Object[]{"12.00"};
        quickServer.setStoreObjects(store);

        //设置管理QuickServer的server
        quickServer.setQSAdminServerPort(4124);
        quickServer.getQSAdminServer().getServer().setName("EchoAdminServer v1.0");

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
