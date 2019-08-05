package org.liu.echoserver;

import org.quickserver.net.server.ClientCommandHandler;
import org.quickserver.net.server.ClientHandler;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class EchoCommandHandler implements ClientCommandHandler {
    @Override
    public void handleCommand(ClientHandler clientHandler, String command) throws SocketTimeoutException, IOException {
        if ("Quit".equals(command)) {
            clientHandler.sendClientMsg("Bye ;-)");
            clientHandler.closeConnection();
        } else {
            clientHandler.sendClientMsg("Echo:" + command);
        }
    }

    @Override
    public void gotConnected(ClientHandler clientHandler) throws SocketTimeoutException, IOException {
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
