package org.liu.echoserver;

import org.quickserver.net.AppException;
import org.quickserver.net.server.AuthStatus;
import org.quickserver.net.server.ClientData;
import org.quickserver.net.server.ClientHandler;
import org.quickserver.net.server.QuickAuthenticationHandler;

import java.io.IOException;
import java.util.Arrays;

public class EchoClientAuthenticationHandler extends QuickAuthenticationHandler {
    @Override
    public AuthStatus askAuthentication(ClientHandler clientHandler) throws IOException, AppException {
        EchoServerData clientData = (EchoServerData)clientHandler.getClientData();
        clientData.setLastAsked("U");
        clientHandler.sendClientMsg("User Name :");
        return null;
    }

    @Override
    public AuthStatus handleAuthentication(ClientHandler handler, byte[] data) throws IOException {
        try {
            return handleAuthentication(handler, new String(data, "utf-8"));
        } catch (AppException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AuthStatus handleAuthentication(ClientHandler handler, String data) throws IOException, AppException {
        EchoServerData clientData = (EchoServerData)handler.getClientData();

        if ("U".equals(clientData.getLastAsked())){
            clientData.setLastAsked("P");
            clientData.setUsername(data);
            handler.sendClientMsg("Password :");
        } else if ("P".equals(clientData.getLastAsked())){
            clientData.setPassword(data.getBytes());

            if (validate(clientData.getUsername(), clientData.getPassword())) {
                handler.sendClientMsg("Auth OK");
                clientData.setPassword(null);
                return AuthStatus.SUCCESS;
            } else {
                return AuthStatus.FAILURE;
            }
        } else {
            throw new AppException("Unknown LastAsked!");
        }
        return null;
    }

    private boolean validate(String username, byte[] password) {
        return Arrays.equals(username.getBytes(), password);
    }
}
