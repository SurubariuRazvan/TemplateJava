package com.exam.client.gui;

import com.exam.client.controller.LoginController;
import com.exam.service.IAppServices;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Gui extends Application {
    public static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Application Login");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/LoginView.fxml"));
        StackPane rootLayout = loader.load();
        LoginController loginController = loader.getController();

        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IAppServices server = (IAppServices) factory.getBean("appService");

        loginController.setService(server);
        loginController.setPrimaryStage(primaryStage);

        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }
}
