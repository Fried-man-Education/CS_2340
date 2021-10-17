package com.tower_defense.tower_defense;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        loadStage("TitleScreen.fxml", stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public void loadStage(final String where, final Stage stage)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(where));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Tower Defense");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
