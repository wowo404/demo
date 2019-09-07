package org.liu.echoserver;

import lombok.Getter;
import lombok.Setter;
import org.quickserver.net.server.ClientData;
import org.quickserver.net.server.ClientIdentifiable;

@Getter
@Setter
public class EchoServerData implements ClientData, ClientIdentifiable {
    private int helloCount;
    private String username;
    private String lastAsked;
    private byte password[];

    @Override
    public String getClientId() {
        return username;
    }

    @Override
    public String getClientKey() {
        return username;
    }

    @Override
    public String getClientInfo() {
        return username;
    }
}
