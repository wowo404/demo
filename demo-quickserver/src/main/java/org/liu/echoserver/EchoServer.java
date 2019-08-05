package org.liu.echoserver;

import org.quickserver.net.AppException;
import org.quickserver.net.server.QuickServer;

public class EchoServer {

    public static void main(String[] args) {
        QuickServer quickServer = new QuickServer("org.liu.echoserver.EchoCommandHandler");
        quickServer.setPort(4123);
        quickServer.setName("Echo Server v1.0");
        quickServer.setAuthenticator("org.liu.echoserver.EchoServerQuickAuthenticator");
        try {
            quickServer.startServer();
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

}
