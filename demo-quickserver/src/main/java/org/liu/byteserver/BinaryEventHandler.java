package org.liu.byteserver;

import org.quickserver.net.server.ClientCommandHandler;
import org.quickserver.net.server.ClientEventHandler;
import org.quickserver.net.server.ClientHandler;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class BinaryEventHandler implements ClientEventHandler {

    @Override
    public void gotConnected(ClientHandler clientHandler) throws SocketTimeoutException, IOException {
        clientHandler.sendClientBinary("welcome byte server".getBytes());
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
