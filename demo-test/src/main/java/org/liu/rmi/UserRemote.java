package org.liu.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserRemote extends Remote {

    String getUserName(Long id) throws RemoteException;

}
