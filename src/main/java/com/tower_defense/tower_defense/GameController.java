package com.tower_defense.tower_defense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends MainApplication {

    private static String difficulty;
    private static String name;
    @FXML Label MoneyLabel;
    @FXML Label HealthLabel;

    @FXML
    public void initialize() {
        MoneyLabel.setText("Money: " + difficultMoney(difficulty));
        HealthLabel.setText("Health: " + difficultHealth(difficulty));
    }

    public String difficultMoney (String difficulty) {
        int startingMoney = 1000;
        return calculateValue(difficulty, startingMoney);
    }

    public String difficultHealth (String difficulty) {
        int startingHealth = 200;
        return calculateValue(difficulty, startingHealth);
    }

    private String calculateValue(String difficulty, int num) {
        return switch (difficulty) {
            case "Easy" -> String.valueOf(num);
            case "Medium" -> String.valueOf(num / 2);
            case "Hard" -> String.valueOf(num / 4);
            default -> throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        };
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        GameController.name = name;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        GameController.difficulty = difficulty;
    }
}
