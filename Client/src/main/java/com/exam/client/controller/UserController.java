package com.exam.client.controller;

import com.exam.domain.Employee;
import com.exam.domain.Manager;
import com.exam.domain.Task;
import com.exam.domain.User;
import com.exam.service.AppServiceException;
import com.exam.service.IAppServices;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.util.function.BiConsumer;
import java.util.function.Supplier;


public abstract class UserController<U extends User> implements Initializable, IUserController {
    public StackPane rootPane;
    public BorderPane menuPane;
    protected IAppServices appService;
    protected User user;

    public void setService(IAppServices appService, User user) {
        this.appService = appService;
        this.user = user;
        postInitialization();
    }

    protected abstract void postInitialization();

    @Override
    public void logout() {
        try {
            appService.logout(user.getId());
        } catch (AppServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWindows(Task task) {
    }

    @Override
    public void employeeLoggedIn(Employee employee) {
    }

    @Override
    public void employeeLoggedOut(Employee employee) {
    }

    @Override
    public void updateAdministratorWindow(Employee employee, Manager manager, Boolean isDelete) {
    }
}
