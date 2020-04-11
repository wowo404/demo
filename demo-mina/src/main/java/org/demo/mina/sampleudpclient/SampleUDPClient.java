package org.demo.mina.sampleudpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;
import org.demo.mina.sampleudpserver.SampleUDPServer;

import java.net.InetSocketAddress;

@Slf4j
public class SampleUDPClient extends IoHandlerAdapter {

    public static void main(String[] args) {
        SampleUDPClient client = new SampleUDPClient();
        client.client();
    }

    private IoSession session;

    public void client() {
        NioDatagramConnector connector = new NioDatagramConnector();
        connector.setHandler(this);
        ConnectFuture connFuture = connector.connect(new InetSocketAddress("localhost", SampleUDPServer.PORT));
        connFuture.addListener(new IoFutureListener() {
            public void operationComplete(IoFuture future) {
                ConnectFuture connFuture = (ConnectFuture) future;
                if (connFuture.isConnected()) {
                    session = future.getSession();
                    try {
                        sendData();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    log.error("Not connected...exiting");
                }
            }
        });
    }

    private void sendData() throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            long free = Runtime.getRuntime().freeMemory();
            System.out.println("times:" + i + ", free memory:" + free);
            IoBuffer buffer = IoBuffer.allocate(8);
            buffer.putLong(free);
            buffer.flip();
            session.write(buffer);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new InterruptedException(e.getMessage());
            }
        }
    }

}
