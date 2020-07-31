package org.liu.rmi.client;

import org.liu.rmi.UserRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TestClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String rmi = "rmi://127.0.0.1:9065/UserRemote";
        UserRemote userRemote = (UserRemote) Naming.lookup(rmi);
        String userName = userRemote.getUserName(1L);
        System.out.println(userName);
    }

}
