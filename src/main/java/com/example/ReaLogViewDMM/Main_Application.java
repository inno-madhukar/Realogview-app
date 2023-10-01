package com.example.ReaLogViewDMM;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class Main_Application extends Application {


    private double x = 0;
    private double y = 0;
    public Stage primstage;

    @Override
    public void start(Stage stage) throws IOException, Exception {
        // Get a list of all the available serial ports
        primstage = stage;
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("DMM_APP_GUI.fxml"));
        Scene scene = new Scene(fxmlLoader, 1166, 665);
        fxmlLoader.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        fxmlLoader.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(.8);
        });

        fxmlLoader.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1);
        });
//        Image image00 = new Image(getClass().getResource("/images/dmmlogo.jpg").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/temp logo.jpg")));

        stage.setScene(scene);
        stage.setTitle("ReLogView DMM 1.0");
//        stage.initStyle(StageStyle.UNIFIED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

