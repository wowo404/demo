package org.liu.echoserver;

import org.quickserver.net.AppException;
import org.quickserver.net.server.ClientHandler;
import org.quickserver.net.server.QuickAuthenticator;

import java.io.IOException;

public class EchoServerQuickAuthenticator extends QuickAuthenticator {
    @Override
    public boolean askAuthorisation(ClientHandler clientHandler) throws IOException, AppException {
        String username = askStringInput(clientHandler, "Username:");
        String password = askStringInput(clientHandler, "Password:");
        if (username == null || password == null) {
            return false;
        }
        if (username.equals(password)) {
            sendString(clientHandler, "Auth ok");
            return true;
        } else {
            sendString(clientHandler, "Auth failed");
            return false;
        }
    }
}
