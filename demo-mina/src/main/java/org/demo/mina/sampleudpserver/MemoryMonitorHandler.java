package org.demo.mina.sampleudpserver;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.net.SocketAddress;

public class MemoryMonitorHandler extends IoHandlerAdapter {

    private SampleUDPServer server;

    public MemoryMonitorHandler(SampleUDPServer sampleUDPServer){
        this.server = sampleUDPServer;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        SocketAddress remoteAddress = session.getRemoteAddress();
        server.addClient(remoteAddress);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("Session closed...");
        SocketAddress remoteAddress = session.getRemoteAddress();
        server.removeClient(remoteAddress);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        if (message instanceof IoBuffer) {
            IoBuffer buffer = (IoBuffer) message;
            SocketAddress remoteAddress = session.getRemoteAddress();
            System.out.println("current receive is:" + buffer.getLong());
            server.recvUpdate(remoteAddress, buffer.getLong());
        }
    }
}
