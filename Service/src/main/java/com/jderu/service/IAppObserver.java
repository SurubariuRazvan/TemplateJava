package com.jderu.service;

import com.jderu.domain.Employee;
import com.jderu.domain.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAppObserver extends Remote {
    void updateWindows(Task task) throws RemoteException;

    void employeeLoggedIn(Employee employee) throws RemoteException;

    void employeeLoggedOut(Employee employee) throws RemoteException;
}
