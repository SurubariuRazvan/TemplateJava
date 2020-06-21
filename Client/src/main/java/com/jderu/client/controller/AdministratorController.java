package com.jderu.client.controller;

import com.jderu.domain.*;
import com.jderu.service.AppServiceException;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class AdministratorController extends UserController<Administrator> {

    public TableView<Employee> employeeTable;
    public TableColumn<Employee, String> employeeNameColumn;
    public TableColumn<Employee, Void> employeeDeleteColumn;
    public JFXTextField employeeUpdateUsername;
    public JFXTextField employeeUpdatePassword;
    public JFXTextField employeeUpdateName;
    public JFXTextField employeeAddUsername;
    public JFXTextField employeeAddName;
    public JFXTextField employeeAddPassword;
    public TableView<Manager> managerTable;
    public TableColumn<Manager, String> managerNameColumn;
    public TableColumn<Manager, Void> managerDeleteColumn;
    public JFXTextField managerUpdateUsername;
    public JFXTextField managerUpdatePassword;
    public JFXTextField managerUpdateName;
    public JFXTextField managerAddUsername;
    public JFXTextField managerAddName;
    public JFXTextField managerAddPassword;

    protected ObservableList<Employee> employees;
    protected ObservableList<Manager> managers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addButtonToTable(employeeDeleteColumn, "deleteButton", () -> new MaterialDesignIconView(MaterialDesignIcon.MINUS_CIRCLE_OUTLINE, "30"), (index, user) -> deleteUser(user));

        managerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addButtonToTable(managerDeleteColumn, "deleteButton", () -> new MaterialDesignIconView(MaterialDesignIcon.MINUS_CIRCLE_OUTLINE, "30"), (index, user) -> deleteUser(user));

        addTextLimiter(employeeUpdateUsername, 100);
        addTextLimiter(employeeUpdatePassword, 100);
        addTextLimiter(employeeUpdateName, 100);
        addTextLimiter(employeeAddUsername, 100);
        addTextLimiter(employeeAddPassword, 100);
        addTextLimiter(employeeAddName, 100);
    }

    private void addUser(User user) {
        try {
            appService.addUser(user);
        } catch (AppServiceException e) {
            showError("Error", e.getMessage());
        }
    }

    private void updateUser(User user) {
        try {
            appService.updateUser(user);
        } catch (AppServiceException e) {
            showError("Error", e.getMessage());
        }
    }

    private void deleteUser(User user) {
        try {
            appService.deleteUser(user.getId());
        } catch (AppServiceException e) {
            showError("Error", e.getMessage());
        }
    }

    protected void postInitialization() {
        try {
            employees = FXCollections.observableList(appService.findAllEmployees());
            employeeTable.setItems(employees);
        } catch (AppServiceException e) {
            e.printStackTrace();
        }
        try {
            managers = FXCollections.observableList(appService.findAllManagers());
            managerTable.setItems(managers);
        } catch (AppServiceException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeUsername(ActionEvent actionEvent) {
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        employee.setUsername(employeeUpdateUsername.getText());
        updateUser(employee);
    }

    public void updateEmployeePassword(ActionEvent actionEvent) {
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        employee.setPassword(User.encodePassword(employeeUpdatePassword.getText()));
        updateUser(employee);
    }

    public void updateEmployeeName(ActionEvent actionEvent) {
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        employee.setName(employeeUpdateName.getText());
        updateUser(employee);
    }

    public void addEmployee(ActionEvent actionEvent) {
        addUser(new Employee(null, employeeAddUsername.getText(), User.encodePassword(employeeAddPassword.getText()), employeeAddName.getText()));
    }

    public void updateManagerUsername(ActionEvent actionEvent) {
        Manager manager = managerTable.getSelectionModel().getSelectedItem();
        manager.setUsername(managerUpdateUsername.getText());
        updateUser(manager);
    }

    public void updateManagerPassword(ActionEvent actionEvent) {
        Manager manager = managerTable.getSelectionModel().getSelectedItem();
        manager.setPassword(User.encodePassword(managerUpdatePassword.getText()));
        updateUser(manager);
    }

    public void updateManagerName(ActionEvent actionEvent) {
        Manager manager = managerTable.getSelectionModel().getSelectedItem();
        manager.setName(managerUpdateName.getText());
        updateUser(manager);
    }

    public void addManager(ActionEvent actionEvent) {
        addUser(new Manager(null, managerAddUsername.getText(), User.encodePassword(managerAddPassword.getText()), managerAddName.getText()));
    }

    @Override
    public void updateAdministratorWindow(Employee employee, Manager manager, Boolean isDelete) {
        System.out.println("Administrator notified");
        if (employee != null) {
            int index = employeeTable.getItems().indexOf(employee);
            if (isDelete)
                employeeTable.getItems().remove(index);
            else if (index != -1)
                employeeTable.getItems().set(index, employee);
            else
                employeeTable.getItems().add(employee);
        }
        if (manager != null) {
            int index = managerTable.getItems().indexOf(manager);
            if (isDelete)
                managerTable.getItems().remove(index);
            else if (index != -1)
                managerTable.getItems().set(index, manager);
            else
                managerTable.getItems().add(manager);
        }
    }
}
