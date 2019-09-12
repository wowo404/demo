package org.liu.objectserver;

import org.quickserver.net.server.ClientHandler;
import org.quickserver.net.server.ClientObjectHandler;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class ObjectHandler implements ClientObjectHandler {
    @Override
    public void handleObject(ClientHandler clientHandler, Object o) throws SocketTimeoutException, IOException {
        DataTransport dataTransport = (DataTransport) o;
        System.out.println(dataTransport);
        dataTransport.setCallback("This is Apple");
        clientHandler.sendClientObject(dataTransport);
    }
}
