package org.liu.echoserver;

import org.quickserver.net.server.*;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class EchoCommandHandler implements ClientCommandHandler, ClientEventHandler {
    @Override
    public void handleCommand(ClientHandler clientHandler, String command) throws SocketTimeoutException, IOException {
        System.out.println("EchoCommandHandler receive msg : " + command);
        DataMode dataModeIn = clientHandler.getDataMode(DataType.IN);
        System.out.println("dataModeIn:" + dataModeIn);
        DataMode dataModeOut = clientHandler.getDataMode(DataType.OUT);
        System.out.println("dataModeOut:" + dataModeOut);
        if ("Quit".equals(command)) {
            clientHandler.sendClientMsg("Bye ;-)");
            clientHandler.closeConnection();
        } else if ("hello".equalsIgnoreCase(command)){
            EchoServerData data = (EchoServerData)clientHandler.getClientData();
            data.setHelloCount(data.getHelloCount() + 1);
            if (data.getHelloCount() == 1) {
                clientHandler.sendClientMsg("Hello " + data.getUsername());
            } else {
                clientHandler.sendClientMsg("You told hello " + data.getHelloCount() + " times");
            }
        } else if ("What's interest?".equalsIgnoreCase(command)){
            String interest = (String)clientHandler.getServer().getStoreObjects()[0];
            clientHandler.sendClientMsg("Interest is : " + interest + "%");
        } else {
            clientHandler.sendClientMsg("Echo:" + command);
        }
    }

    @Override
    public void gotConnected(ClientHandler clientHandler) throws SocketTimeoutException, IOException {
        //默认日志级别就是INFO
        clientHandler.sendSystemMsg("New client : " + clientHandler.getSocket().getInetAddress().getHostAddress());
        clientHandler.sendClientMsg("++++++++++++++++++++");
        clientHandler.sendClientMsg("| Welcome to EchoServer v1.0 |");
        clientHandler.sendClientMsg("| Send 'Quit' to exit |");
        clientHandler.sendClientMsg("++++++++++++++++++++");
    }

    @Override
    public void lostConnection(ClientHandler clientHandler) throws IOException {
        clientHandler.sendSystemMsg("Connection lost:" + clientHandler.getSocket().getInetAddress());
    }

    @Override
    public void closingConnection(ClientHandler clientHandler) throws IOException {
        clientHandler.sendSystemMsg("Closing connection:" + clientHandler.getSocket().getInetAddress());
    }
}
