package org.liu.echoserver;

import org.quickserver.net.server.ClientBinaryHandler;
import org.quickserver.net.server.ClientHandler;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class EchoClientBinaryHandler implements ClientBinaryHandler {
    @Override
    public void handleBinary(ClientHandler clientHandler, byte[] bytes) throws SocketTimeoutException, IOException {
        String data = new String(bytes, "utf-8");
        System.out.println("receive msg : " + data);
        clientHandler.sendSystemMsg("receive msg : " + data);
        clientHandler.sendClientBytes("You send msg is : " + data);
    }
}
