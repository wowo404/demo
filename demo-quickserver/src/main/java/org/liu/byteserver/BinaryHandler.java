package org.liu.byteserver;

import org.quickserver.net.server.ClientBinaryHandler;
import org.quickserver.net.server.ClientHandler;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class BinaryHandler implements ClientBinaryHandler {
    @Override
    public void handleBinary(ClientHandler clientHandler, byte[] bytes) throws SocketTimeoutException, IOException {
        System.out.println(new String(bytes));
        clientHandler.sendClientBinary("ok!".getBytes());
    }
}
