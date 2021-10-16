package com.tower_defense.tower_defense;

import com.tower_defense.tower_defense.towers.AbstractTower;
import com.tower_defense.tower_defense.towers.MachineTower;
import com.tower_defense.tower_defense.towers.NormalTower;
import com.tower_defense.tower_defense.towers.SplashTower;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;


public class GameController extends MainApplication {

    private static String difficulty;
    private static String name;
    private int money;
    private int health;
    private List<AbstractTower> towers;
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

    public void buildTower(int towerType, int row, int col) {
        AbstractTower tower;
        switch (towerType) {
            case 0:
                tower = new NormalTower();
                break;
            case 1:
                tower = new SplashTower();
                break;
            case 2:
                tower = new MachineTower();
                break;
            default:
                tower = new NormalTower();
        }
        if (tower.cost > this.money) {
            System.out.println("You do not have enough money to print this tower");
            return;
        } else {
            // purchase the tower and deduct money
            this.setMoney(this.money - tower.cost);
        }
        tower.place(row, col);
        towers.add(tower);
    }
}
