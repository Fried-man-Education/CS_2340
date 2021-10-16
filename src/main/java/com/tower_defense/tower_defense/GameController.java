package com.tower_defense.tower_defense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class GameController extends MainApplication {

    private static String difficulty;
    private static String name;
    private int money;
    private int health;
    @FXML private Label moneyLabel;
    @FXML private Label healthLabel;


    @FXML
    public void initialize() {
        moneyLabel.setText("Money: " + difficultMoney(difficulty));
        healthLabel.setText("Health: " + difficultHealth(difficulty));
    }

    public String difficultMoney(String difficulty) {
        int startingMoney = 1000;
        return calculateValue(difficulty, startingMoney);
    }

    public String difficultHealth(String difficulty) {
        int startingHealth = 200;
        return calculateValue(difficulty, startingHealth);
    }

    private String calculateValue(String difficulty, int num) {
        switch (difficulty) {
        case "Easy":
            return String.valueOf(num);
        case "Medium":
            return String.valueOf(num / 2);
        case "Hard":
            return String.valueOf(num / 4);
        default:
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
    }
//    public void BlueButtonoonAction (ActionEvent actionEvent) throws IOException {
//
//    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        GameController.name = name.trim();
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        GameController.difficulty = difficulty;
    }

    @FXML
    public void onGridClicked(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        System.out.println("(" + colIndex + ", " + rowIndex + ")");
    }

    public void setMoney(int newMoney) {
        this.money = newMoney;
        moneyLabel.setText("Money: " + this.money);
    }

    public int getMoney() {
        return this.money;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
        healthLabel.setText("Health: " + this.health);
    }

    public int getHealth() {
        return this.health;
    }
}
