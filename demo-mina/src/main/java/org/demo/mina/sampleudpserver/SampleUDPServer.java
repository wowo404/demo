package org.demo.mina.sampleudpserver;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

public class SampleUDPServer {

    public static final int PORT = 9410;
    private static Map<SocketAddress, Long> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        SampleUDPServer sampleUDPServer = new SampleUDPServer();
        sampleUDPServer.startServer();
    }

    private void startServer() throws IOException {
        NioDatagramAcceptor acceptor = new NioDatagramAcceptor();
        acceptor.setHandler(new MemoryMonitorHandler(this));

        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        chain.addLast("logger", new LoggingFilter());

        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);
        acceptor.bind(new InetSocketAddress(PORT));
    }

    public void addClient(SocketAddress address){
        map.put(address, 0L);
    }

    public void removeClient(SocketAddress remoteAddress) {
        map.remove(remoteAddress);
    }

    public void recvUpdate(SocketAddress remoteAddress, long aLong) {
        Long receiveBytesLength = map.get(remoteAddress);
        map.put(remoteAddress, receiveBytesLength + aLong);
    }
}
