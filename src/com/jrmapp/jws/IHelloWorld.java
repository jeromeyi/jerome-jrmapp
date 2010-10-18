package com.jrmapp.jws;

import java.rmi.RemoteException;

public interface IHelloWorld {
	public String getMessage(String name) throws RemoteException;
}
