package com.exam.client.controller;

import com.exam.domain.*;
import com.exam.service.AppServiceException;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AdministratorController extends UserController<Administrator> {

    public TableView<Employee> employeeTable;
    public TableColumn<Employee, String> employeeNameColumn;
    public TableColumn<Employee, Void> employeeDeleteColumn;
    public TextField employeeUpdateUsername;
    public TextField employeeUpdatePassword;
    public TextField employeeUpdateName;
    public TextField employeeAddUsername;
    public TextField employeeAddName;
    public TextField employeeAddPassword;
    public TableView<Manager> managerTable;
    public TableColumn<Manager, String> managerNameColumn;
    public TableColumn<Manager, Void> managerDeleteColumn;
    public TextField managerUpdateUsername;
    public TextField managerUpdatePassword;
    public TextField managerUpdateName;
    public TextField managerAddUsername;
    public TextField managerAddName;
    public TextField managerAddPassword;

    protected ObservableList<Employee> employees;
    protected ObservableList<Manager> managers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        GuiUtility.addButtonToTable(employeeDeleteColumn, "deleteButton", () -> new MaterialDesignIconView(MaterialDesignIcon.MINUS_CIRCLE_OUTLINE, "30"), (index, user) -> {
            try {
                deleteUser(user);
            } catch (Exception e) {
                GuiUtility.showError(rootPane, menuPane, "Eroare", e.getMessage());
            }
        });

        managerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        GuiUtility.addButtonToTable(managerDeleteColumn, "deleteButton", () -> new MaterialDesignIconView(MaterialDesignIcon.MINUS_CIRCLE_OUTLINE, "30"), (index, user) -> {
            try {
                deleteUser(user);
            } catch (Exception e) {
                GuiUtility.showError(rootPane, menuPane, "Eroare", e.getMessage());
            }
        });

        GuiUtility.addTextLimiter(employeeUpdateUsername, 100);
        GuiUtility.addTextLimiter(employeeUpdatePassword, 100);
        GuiUtility.addTextLimiter(employeeUpdateName, 100);
        GuiUtility.addTextLimiter(employeeAddUsername, 100);
        GuiUtility.addTextLimiter(employeeAddPassword, 100);
        GuiUtility.addTextLimiter(employeeAddName, 100);
    }

    private void addUser(User user) {
        try {
            appService.addUser(user);
        } catch (AppServiceException e) {
            GuiUtility.showError(rootPane, menuPane, "Error", e.getMessage());
        }
    }

    private void updateUser(User user) {
        try {
            appService.updateUser(user);
        } catch (AppServiceException e) {
            GuiUtility.showError(rootPane, menuPane, "Error", e.getMessage());
        }
    }

    private void deleteUser(User user) {
        try {
            appService.deleteUser(user.getId());
        } catch (AppServiceException e) {
            GuiUtility.showError(rootPane, menuPane, "Error", e.getMessage());
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
