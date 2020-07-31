package org.liu.rmi.impl;

import org.liu.rmi.UserRemote;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class UserRemoteImpl extends UnicastRemoteObject implements UserRemote {
    protected UserRemoteImpl() throws RemoteException {
    }

    protected UserRemoteImpl(int port) throws RemoteException {
        super(port);
    }

    protected UserRemoteImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public String getUserName(Long id) throws RemoteException {
        System.out.println("receive id:" + id);
        return id > 100 ? "liu" : "zhang";
    }
}
