package com.jderu.client.controller;

import com.jderu.domain.Employee;
import com.jderu.domain.Task;
import com.jderu.service.AppServiceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController extends UserController<Employee> {
    public TableView<Task> taskTable;
    public TableColumn<Task, String> taskDescription;
    protected ObservableList<Task> entities;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

//        taskDescription.setCellFactory(tc -> {
//            TableCell<Task, String> cell = new TableCell<>();
//            Text text = new Text();
//            text.getStyleClass().add("table-text-cell");
//            cell.getStyleClass().add("table-text-cell");
//            cell.setGraphic(text);
//            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
//            text.wrappingWidthProperty().bind(taskDescription.widthProperty());
//            text.textProperty().bind(cell.itemProperty());
//            return cell;
//        });
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
