package com.tower_defense.tower_defense;

import com.tower_defense.tower_defense.towers.AbstractTower;
import com.tower_defense.tower_defense.towers.MachineTower;
import com.tower_defense.tower_defense.towers.NormalTower;
import com.tower_defense.tower_defense.towers.SplashTower;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class GameController extends MainApplication {

    private static String difficulty;
    private static String name;
    private int money;
    private int health;
    private List<AbstractTower> towers;
    @FXML private Label moneyLabel;
    @FXML private Label healthLabel;
    private static final Map<String, Integer> towerMap = new HashMap<>(); // 0 = Normal, 1 = Splash, 2 = Machine
    private static int selectedTower = -1; // -1 = none selected, 0 = normal, 1 = splash, 2 = machine
    private static final Paint[] colors = new Paint[]{Color.web("0x1e90ffff"), Color.web("0xd300e6ff"), Color.web("0xff8e21ff")};
    private static Circle lastCircle;

    @FXML
    public void initialize() {
        towerMap.put("0x1e90ffff", 0);
        towerMap.put("0xd300e6ff", 1);
        towerMap.put("0xff8e21ff", 2);

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
        return switch (difficulty) {
            case "Easy" -> String.valueOf(num);
            case "Medium" -> String.valueOf(num / 2);
            case "Hard" -> String.valueOf(num / 4);
            default -> throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        };
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
        Integer x = GridPane.getColumnIndex(clickedNode);
        Integer y = GridPane.getRowIndex(clickedNode);
        System.out.println("(" + x + ", " + y + ")");

        placeTower(x, y);
    }

    @FXML
    public void onHoldTower(MouseEvent event) {
        Circle circle = (Circle) (event.getPickResult().getIntersectedNode());
        String color = circle.getFill().toString();
        int tower = towerMap.getOrDefault(color, selectedTower); // If the color is not in the map, then it must be faded (selected already)

        // ReClick the tower they already have selected, unselect
        if (tower == selectedTower) {
            if (tower != -1) {
                circle.setFill(colors[tower]);
            }
            selectedTower = -1;
        } else { // Update selected tower
            unselectLastTower();
            circle.setFill(Color.GRAY); // Set current tower to gray
            selectedTower = tower; // update selected tower
        }

        lastCircle = circle;
        System.out.println(selectedTower);
    }

    private void placeTower(int x, int y) {
        // TODO check that not on path
        AbstractTower tower = switch (selectedTower) {
            case 1 -> new SplashTower();
            case 2 -> new MachineTower();
            default -> new NormalTower();
        };
        unselectLastTower();

        if (tower.cost > this.money) {
            System.out.println("You do not have enough money to print this tower");
            return;
        } else {
            // purchase the tower and deduct money
            this.setMoney(this.money - tower.cost);
        }
        tower.place(x, y);
        towers.add(tower);

    }

    private void unselectLastTower() {
        if (lastCircle == null || selectedTower == -1) return;
        lastCircle.setFill(colors[selectedTower]);
        selectedTower = -1;
        lastCircle = null;
    }
}
