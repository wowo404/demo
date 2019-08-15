package org.liu.echoserver;

import org.quickserver.net.server.ClientExtendedEventHandler;
import org.quickserver.net.server.ClientHandler;

import java.io.IOException;
import java.net.SocketException;

public class EchoClientExtendedEventHandler implements ClientExtendedEventHandler {
    @Override
    public void handleTimeout(ClientHandler clientHandler) throws SocketException, IOException {
        clientHandler.sendClientMsg("-ERR Timeout");
        throw new SocketException();
    }

    @Override
    public void handleMaxAuthTry(ClientHandler clientHandler) throws IOException {
        clientHandler.sendClientMsg("-ERR Max Auth Try Reached");
    }

    @Override
    public boolean handleMaxConnection(ClientHandler clientHandler) throws IOException {
        clientHandler.sendClientMsg("Server Busy - Max Connection Reached");
        return false;
    }
}
