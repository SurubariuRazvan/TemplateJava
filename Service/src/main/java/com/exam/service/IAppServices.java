package com.exam.service;

import com.exam.domain.*;

import java.util.List;

public interface IAppServices {
    void sendTask(Employee employee, String taskDescription) throws AppServiceException;

    void addUser(User user) throws AppServiceException;

    void updateUser(User user) throws AppServiceException;

    void deleteUser(Integer userId) throws AppServiceException;

    List<Employee> findAllWorkingEmployees() throws AppServiceException;

    List<Task> findAllTasksForEmployee(Employee employee) throws AppServiceException;

    User login(String username, String password, IAppObserver client) throws AppServiceException;

    void logout(Integer userID) throws AppServiceException;

    List<Employee> findAllEmployees() throws AppServiceException;

    List<Manager> findAllManagers() throws AppServiceException;
}
