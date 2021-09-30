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
    public static String name;
    public static String difficulty;
    @FXML Label MoneyLabel;
    @FXML Label HealthLabel;

    @FXML
    public void initialize() {
        MoneyLabel.setText("Money: " + difficultMoney(difficulty));
        HealthLabel.setText("Health: " + difficultHealth(difficulty));
    }

    public String difficultMoney (String difficulty) {
        int startingMoney = 1000;
        int temp;
        switch (difficulty) {
            case "Easy":
                temp  = startingMoney;
                break;
            case "Medium":
                temp  = startingMoney / 2;
                break;
            case "Hard":
                temp  = startingMoney / 4;
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        return String.valueOf(temp);
    }

    public String difficultHealth (String difficulty) {
        int startingMoney = 200;
        int temp;
        switch (difficulty) {
            case "Easy":
                temp  = startingMoney;
                break;
            case "Medium":
                temp  = startingMoney / 2;
                break;
            case "Hard":
                temp  = startingMoney / 4;
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        return String.valueOf(temp);
    }
}
