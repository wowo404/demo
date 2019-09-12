package org.liu.objectserver;

import org.quickserver.net.server.ClientEventHandler;
import org.quickserver.net.server.ClientHandler;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Date;

public class ObjectEventHandler implements ClientEventHandler {

    @Override
    public void gotConnected(ClientHandler clientHandler) throws SocketTimeoutException, IOException {
        System.out.println("get one connection");
        Hello hello = new Hello();
        hello.setServerName("Object Server v1");
        hello.setServerTime(new Date());
        clientHandler.sendClientObject(hello);
    }

    @Override
    public void lostConnection(ClientHandler clientHandler) throws IOException {
        System.out.println("lost connection");
        clientHandler.sendSystemMsg("lost connection");
    }

    @Override
    public void closingConnection(ClientHandler clientHandler) throws IOException {
        System.out.println("closing connection");
        clientHandler.sendSystemMsg("closing connection");
    }
}
