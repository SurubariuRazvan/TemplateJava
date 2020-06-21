package com.exam.client.controller;

import com.exam.domain.Employee;
import com.exam.domain.Task;
import com.exam.service.AppServiceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController extends UserController<Employee> {
    public TableView<Task> taskTable;
    public TableColumn<Task, String> taskDescription;
    protected ObservableList<Task> entities;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    protected void postInitialization() {
        try {
            var a = appService.findAllTasksForEmployee((Employee) user);
            entities = FXCollections.observableList(a);
        } catch (AppServiceException e) {
            e.printStackTrace();
        }
        taskTable.setItems(entities);
    }

    @Override
    public void updateWindows(Task task) {
        taskTable.getItems().add(task);
    }
}
