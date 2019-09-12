package org.liu.byteserver;

import org.quickserver.net.server.ClientCommandHandler;
import org.quickserver.net.server.ClientHandler;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class BinaryCommandHandler implements ClientCommandHandler {
    @Override
    public void handleCommand(ClientHandler clientHandler, String s) throws SocketTimeoutException, IOException {
        System.out.println("receive message:" + s);
    }
}
