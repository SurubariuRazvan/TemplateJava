package com.jderu.client.controller;

import com.jderu.domain.Employee;
import com.jderu.domain.Manager;
import com.jderu.domain.Task;
import com.jderu.domain.User;
import com.jderu.service.AppServiceException;
import com.jderu.service.IAppObserver;
import com.jderu.service.IAppServices;
import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

public class LoginController extends UnicastRemoteObject implements Initializable, IAppObserver, Serializable {

    public JFXTextField logInUsername;
    public JFXPasswordField logInPassword;
    public JFXButton logInButton;
    public StackPane rootPane;
    public HBox menuTable;
    private IAppServices loginService;
    private Stage primaryStage;
    private IUserController appController;

    public LoginController() throws RemoteException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setService(IAppServices loginService) {
        this.loginService = loginService;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    protected void showError(String title, String message) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OK");
        JFXDialog dialog = new JFXDialog(this.rootPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> dialog.close());

        dialogLayout.setHeading(new Label(title));
        dialogLayout.getStyleClass().add("errorHeading");
        dialogLayout.setBody(new Label(message));
        dialogLayout.setActions(button);
        dialog.show();
        BoxBlur blur = new BoxBlur(3, 3, 2);
        this.menuTable.setEffect(blur);
        dialog.setOnDialogClosed((JFXDialogEvent event) -> this.menuTable.setEffect(null));
    }

    public void login(ActionEvent actionEvent) {
        String username = logInUsername.getText();
        String password = logInPassword.getText();

        try {
            User user = loginService.login(username, password, this);
            if (user == null)
                failedLogin();
            else
                successfulLogin(user);
        } catch (AppServiceException e) {
            showError("Login error", e.getMessage());
        }
    }

    private void failedLogin() {
        logInUsername.getStyleClass().add("wrong-credentials");
        logInPassword.getStyleClass().add("wrong-credentials");
    }

    private void successfulLogin(User user) {
        String controllerPath = "/view/" + user.getUserType().toString() + "View.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerPath));
            StackPane rootLayout = loader.load();
            appController = loader.getController();

            appController.setService(loginService, user);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(500);
            primaryStage.setTitle(user.getUserType().toString() + " Window: " + user.getName());
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.setOnCloseRequest(event -> {
                appController.logout();
                System.exit(0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdministratorWindow(Employee employee, Manager manager, Boolean isDelete) throws RemoteException {
        appController.updateAdministratorWindow(employee, manager, isDelete);
    }

    @Override
    public void updateWindows(Task task) throws RemoteException {
        appController.updateWindows(task);
    }

    @Override
    public void employeeLoggedIn(Employee employee) throws RemoteException {
        appController.employeeLoggedIn(employee);
    }

    @Override
    public void employeeLoggedOut(Employee employee) throws RemoteException {
        appController.employeeLoggedOut(employee);
    }
}
