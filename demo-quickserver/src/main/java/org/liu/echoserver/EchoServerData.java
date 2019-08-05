package org.liu.echoserver;

import lombok.Getter;
import lombok.Setter;
import org.quickserver.net.server.ClientData;

@Getter
@Setter
public class EchoServerData implements ClientData {
    private int helloCount;
    private String username;
}
