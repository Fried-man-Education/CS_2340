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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameController extends MainApplication {

    private static String difficulty;
    private static String name;
    private int money;
    private int health;
    private List<AbstractTower> towers;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label healthLabel;
    @FXML
    private Pane mapPane;

    // 0 = Normal, 1 = Splash, 2 = Machine
    private static final Map<String, Integer> TOWER_MAP = new HashMap<>();
    // -1 = none selected, 0 = normal, 1 = splash, 2 = machine
    private static int selectedTower = -1;
    public static final Paint[] COLORS = new Paint[]{Color.web("0x1e90ff"),
            Color.web("0xd300e6"), Color.web("0xff8e21")};
    private static Rectangle lastRectangle;
    private static GridPane grid;

    @FXML
    public void initialize() {
        mapPane.getChildren().add(createGrid());

        this.towers = new ArrayList<>();
        TOWER_MAP.put("0x1e90ffff", 0);
        TOWER_MAP.put("0xd300e6ff", 1);
        TOWER_MAP.put("0xff8e21ff", 2);

        this.setMoney(difficultMoney(difficulty));
        this.setHealth(difficultHealth(difficulty));
    }

    public GridPane createGrid() {
        grid = new GridPane();
        grid.setPrefHeight(600);
        grid.setPrefWidth(340);
        grid.setOnMouseClicked(this::onGridClicked);

        int numRows = 17;
        int numCols = 25;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (isPath(col, row)) {
                    grid.add(createGrayTile(row, col), col, row);
                } else if (isBase(col, row)) {
                    grid.add(createBaseTile(row, col), col, row);
                } else {
                    grid.add(createGreenTile(row, col), col, row);
                }
            }
        }
        return grid;
    }

    public static GridPane getGrid() {
        return grid;
    }

    public static Rectangle createGrayTile(int row, int col) {
        Rectangle graySquare = new Rectangle();
        graySquare.setId("" + row + "," + col);
        graySquare.setHeight(20);
        graySquare.setWidth(24);
        graySquare.setFill(Color.GREY);
        return graySquare;
    }

    public static Rectangle createGreenTile(int row, int col) {
        Rectangle greenSquare = new Rectangle();
        greenSquare.setId(row + "," + col);
        greenSquare.setHeight(20);
        greenSquare.setWidth(24);
        greenSquare.setFill(Color.GREEN);
        return greenSquare;
    }

    public static Rectangle createBaseTile(int row, int col) {
        Rectangle base = new Rectangle();
        base.setId(row + "," + col);
        base.setHeight(20);
        base.setWidth(24);
        base.setFill(Color.BLUE);
        return base;
    }

    public int difficultMoney(String difficulty) {
        int startingMoney = 1000;
        return calculateValue(difficulty, startingMoney);
    }

    public int difficultHealth(String difficulty) {
        int startingHealth = 200;
        return calculateValue(difficulty, startingHealth);
    }

    private int calculateValue(String difficulty, int num) {
        if (difficulty.equals("Easy")) {
            return num;
        } else if (difficulty.equals("Medium")) {
            return num / 2;
        } else if (difficulty.equals("Hard")) {
            return num / 4;
        } else {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
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

        placeTower(x, y);
    }

    @FXML
    public void onHoldTower(MouseEvent event) {
        Rectangle rectangle = (Rectangle) (event.getPickResult().getIntersectedNode());
        String color = rectangle.getFill().toString();
        // If the color is not in the map, then it must be faded (selected already)
        int tower = TOWER_MAP.getOrDefault(color, selectedTower);

        // ReClick the tower they already have selected, unselect
        if (tower == selectedTower) {
            if (tower != -1) {
                rectangle.setFill(COLORS[tower]);
            }
            selectedTower = -1;
        } else { // Update selected tower
            unselectLastTower();
            rectangle.setFill(Color.GRAY); // Set current tower to gray
            selectedTower = tower; // update selected tower
        }

        lastRectangle = rectangle;
        System.out.println(selectedTower);
    }

    public boolean isPath(int x, int y) {
        if (x == 2 && y > 11) {
            return true;
        } else if (y == 11 && x < 21 && x >= 2) {
            return true;
        } else {
            return x == 21 && y >= 4 && y <= 11;
        }
    }

    private boolean isBase(int x, int y) {
        return y >= 1 && y <= 3 && x >= 20 && x <= 22;
    }

    private int calculateCost(String difficulty, int num) {
        if (difficulty.equals("Easy")) {
            return num / 4;
        } else if (difficulty.equals("Medium")) {
            return num / 2;
        } else if (difficulty.equals("Hard")) {
            return num;
        } else {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
    }

    private void placeTower(int x, int y) {
        if (isPath(x, y) || isBase(x, y)) {
            return;
        }
        for (AbstractTower t : towers) {
            if (t.getX() == x && t.getY() == y) {
                return;
            }
        }

        AbstractTower tower;
        if (selectedTower == 0) {
            tower = new NormalTower(calculateCost(difficulty, 200));
        } else if (selectedTower == 1) {
            tower = new MachineTower(calculateCost(difficulty, 200));
        } else if (selectedTower == 2) {
            tower = new SplashTower(calculateCost(difficulty, 300));
        } else {
            return;
        }
        unselectLastTower();

        if (tower.getCost() > this.money) {
            System.out.println("You do not have enough money to place this tower");
            return;
        } else {
            // purchase the tower and deduct money
            this.setMoney(this.money - tower.getCost());
        }
        tower.place(x, y);
        towers.add(tower);

    }

    private void unselectLastTower() {
        if (lastRectangle != null && selectedTower != -1) {
            lastRectangle.setFill(COLORS[selectedTower]);
        }
        selectedTower = -1;
        lastRectangle = null;
    }
}
