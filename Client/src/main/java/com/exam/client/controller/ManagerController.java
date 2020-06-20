package com.exam.client.controller;

import com.exam.domain.Employee;
import com.exam.domain.Manager;
import com.exam.service.AppServiceException;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ManagerController extends UserController<Manager> {
    public TableView<Employee> employeeTable;
    public TableColumn<Employee, String> employeeName;
    public TableColumn<Employee, String> checkInTime;
    public TableColumn<Employee, Void> action;
    public TextArea taskDescription;
    protected ObservableList<Employee> entities;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        checkInTime.setCellValueFactory(line -> new ReadOnlyObjectWrapper<>(new SimpleDateFormat("HH:mm").format(line.getValue().getLastClockInTime())));
        GuiUtility.addButtonToTable(action, "sendTask", () -> new MaterialDesignIconView(MaterialDesignIcon.SEND, "30"), (index, emp) -> {
            try {
                appService.sendTask(emp, taskDescription.getText());
            } catch (AppServiceException e) {
                GuiUtility.showError(rootPane, menuPane, "Error", e.getMessage());
            }
        });

        GuiUtility.addTextLimiter(taskDescription, 200);
    }


    protected void postInitialization() {
        try {
            entities = FXCollections.observableList(appService.findAllWorkingEmployees());
        } catch (AppServiceException e) {
            e.printStackTrace();
        }
        employeeTable.setItems(entities);
    }

    @Override
    public void employeeLoggedIn(Employee employee) {
        employeeTable.getItems().add(employee);
    }

    @Override
    public void employeeLoggedOut(Employee employee) {
        employeeTable.getItems().remove(employee);
    }
}
