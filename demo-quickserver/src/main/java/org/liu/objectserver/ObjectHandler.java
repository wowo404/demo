package org.liu.objectserver;

import org.quickserver.net.server.ClientHandler;
import org.quickserver.net.server.ClientObjectHandler;
import org.quickserver.net.server.DataMode;
import org.quickserver.net.server.DataType;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class ObjectHandler implements ClientObjectHandler {
    @Override
    public void handleObject(ClientHandler clientHandler, Object o) throws SocketTimeoutException, IOException {
        if (o instanceof DataTransport) {
            DataTransport dataTransport = (DataTransport) o;
            System.out.println(dataTransport);
            dataTransport.setCallback("This is Apple");
            clientHandler.setDataMode(DataMode.OBJECT, DataType.OUT);//发送object之前要设置，垃圾。。。。
            clientHandler.sendClientObject(dataTransport);
        }
        clientHandler.setDataMode(DataMode.STRING, DataType.OUT);//发送完之后要设置回去string，垃圾。。。。
        clientHandler.setDataMode(DataMode.STRING, DataType.IN);
    }
}
