package org.liu.echoserver.qsadmin;

import org.quickserver.net.qsadmin.CommandPlugin;
import org.quickserver.net.server.ClientHandler;
import org.quickserver.net.server.QuickServer;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class QSAdminCommandPlugin implements CommandPlugin {
    @Override
    public boolean handleCommand(ClientHandler clientHandler, String command) throws SocketTimeoutException, IOException {
        QuickServer quickServer = (QuickServer)clientHandler.getServer().getStoreObjects()[0];
        Object[] storeObjects = quickServer.getStoreObjects();

        if (command.toLowerCase().startsWith("set interest ")) {
            storeObjects[0] = command.substring("set interest ".length());
            quickServer.setStoreObjects(storeObjects);
            clientHandler.sendClientMsg("+OK interest changed");
            return true;
        } else if (command.toLowerCase().startsWith("get interest ")){
            clientHandler.sendClientMsg("+OK interest is : " + storeObjects[0]);
            return true;
        }

        return false;
    }
}
