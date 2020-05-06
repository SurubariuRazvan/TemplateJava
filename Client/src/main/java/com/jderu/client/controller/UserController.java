package com.jderu.client.controller;

import com.jderu.domain.Employee;
import com.jderu.domain.User;
import com.jderu.service.AppServiceException;
import com.jderu.service.IAppServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
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

    protected void showError(String title, String message) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OK");
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> dialog.close());

        dialogLayout.setHeading(new Label(title));
        dialogLayout.getStyleClass().add("errorHeading");
        dialogLayout.setBody(new Label(message));
        dialogLayout.setActions(button);
        dialog.show();
        BoxBlur blur = new BoxBlur(3, 3, 2);
        menuPane.setEffect(blur);
        dialog.setOnDialogClosed((JFXDialogEvent event) -> menuPane.setEffect(null));
    }

    public void addTextLimiter(TextField tf, int maxLength) {
        tf.textProperty().addListener((ov, oldValue, newValue) -> {
            if (tf.getText().length() > maxLength)
                tf.setText(tf.getText().substring(0, maxLength));
        });
    }

    public void addTextLimiter(TextArea ta, int maxLength) {
        ta.textProperty().addListener((ov, oldValue, newValue) -> {
            if (ta.getText().length() > maxLength)
                ta.setText(ta.getText().substring(0, maxLength));
        });
    }

    protected void addButtonToTable(TableColumn<Employee, Void> tc, String styleClass, Supplier<Node> graphic, BiConsumer<Integer, Employee> onAction) {
        tc.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                return new TableCell<>() {
                    private final JFXButton btn = new JFXButton(" ");

                    {
                        btn.setGraphic(graphic.get());
                        btn.getStyleClass().add(styleClass);
                        btn.setButtonType(JFXButton.ButtonType.RAISED);
                        btn.setContentDisplay(ContentDisplay.CENTER);
                        btn.setOnAction((ActionEvent event) -> {
                            Employee entity = getTableView().getItems().get(getIndex());
                            try {
                                onAction.accept(getIndex(), entity);
                            } catch (Exception e) {
                                showError("Eroare", e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        });
    }

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
}
