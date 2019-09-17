package org.liu.objectserver;

import org.quickserver.net.server.ClientEventHandler;
import org.quickserver.net.server.ClientHandler;
import org.quickserver.net.server.DataMode;
import org.quickserver.net.server.DataType;

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
        clientHandler.setDataMode(DataMode.OBJECT, DataType.OUT);
        clientHandler.sendClientObject(hello);
        clientHandler.setDataMode(DataMode.STRING, DataType.OUT);//发送完object后要切换回去string，垃圾。。。
        clientHandler.setDataMode(DataMode.OBJECT, DataType.IN);//这里设置表示下一次接收的是个object，即ObjectHandler生效，垃圾。。。
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
