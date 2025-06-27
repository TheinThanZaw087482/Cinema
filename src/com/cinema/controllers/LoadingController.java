package com.cinema.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import com.cinema.models.User;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadingController {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label loadingLabel;

    private User user;
    private String userRole;

    public void initialize() {
        // Don't start loading yet
    }

    public void setUser(User user) {
        this.user = user;
        this.userRole = user.getUserType();
        startLoading(); // Start loading now that we have user data
    }

    private void startLoading() {
        Task<Parent> loadDashboardTask = new Task<Parent>() {
            @Override
            protected Parent call() throws Exception {
                updateMessage("Preparing dashboard...");
                updateProgress(0.3, 1);
                Thread.sleep(300);

                updateMessage("Loading resources...");
                updateProgress(0.6, 1);
                Thread.sleep(300);

                updateMessage("Almost done...");
                updateProgress(0.9, 1);
                Thread.sleep(300);

                FXMLLoader loader = new FXMLLoader();
                if ("admin".equalsIgnoreCase(userRole)) {
                    loader.setLocation(getClass().getResource("/com/cinema/views/dashboardadmin.fxml"));
                    Parent root = loader.load();
                    DashboardControllerAdmin adminController = loader.getController();
                    adminController.displayUsername(user);
                    return root;
                } else {
                    loader.setLocation(getClass().getResource("/com/cinema/views/dashboardstaff.fxml"));
                    Parent root = loader.load();
                    DashboardControllerstaff staffController = loader.getController();
                    staffController.displayUsername(user);
                    return root;
                }
            }
        };

        progressBar.progressProperty().bind(loadDashboardTask.progressProperty());
        loadingLabel.textProperty().bind(loadDashboardTask.messageProperty());

        loadDashboardTask.setOnSucceeded(e -> {
            Platform.runLater(() -> {
                try {
                    Parent dashboardRoot = loadDashboardTask.getValue();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(dashboardRoot));
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();

                    // Close loading screen
                    progressBar.getScene().getWindow().hide();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });

        new Thread(loadDashboardTask).start();
    }
}
