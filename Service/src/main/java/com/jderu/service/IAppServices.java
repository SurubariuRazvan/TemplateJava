package com.jderu.service;

import com.jderu.domain.Employee;
import com.jderu.domain.Task;
import com.jderu.domain.User;
import com.jderu.domain.UserType;

import java.util.List;

public interface IAppServices {
    void sendTask(Employee employee, String taskDescription) throws AppServiceException;

    List<Employee> findAllEmployees() throws AppServiceException;

    List<Task> findAllTasksForEmployee(Employee employee) throws AppServiceException;

    User login(String username, String password, IAppObserver client) throws AppServiceException;

    void logout(Integer userID) throws AppServiceException;
}
