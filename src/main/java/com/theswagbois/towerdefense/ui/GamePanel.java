package com.theswagbois.towerdefense.ui;

import com.theswagbois.towerdefense.models.Combat;
import com.theswagbois.towerdefense.models.Level;
import com.theswagbois.towerdefense.models.Player;
import com.theswagbois.towerdefense.services.Towers;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

public class GamePanel extends Pane {
    private static Rectangle labelsBackground;
    private static Label levelLabel;
    private static Label hpLabel;
    private static Label moneyLabel;
    private static Button startCombatButton;

    private static Color selectedColor;
    private static int selectedIndex = 1;

    private static boolean initialized = false;

    public GamePanel() {
        initializeIcons();
        initializeLevelLabel();
        initializeHpLabel();
        initializeMoneyLabel();
        initializeStartCombatButton();
        initializeBackground();

        this.getChildren().add(labelsBackground);
        this.getChildren().add(levelLabel);
        this.getChildren().add(hpLabel);
        this.getChildren().add(moneyLabel);
        this.getChildren().add(startCombatButton);
        this.setTranslateX(510);
        this.setTranslateY(500);

        initialized = true;
    }

    private void initializeIcons() {
        for (int i = 0; i < Towers.getNumTowers(); i++) {
            int index = i + 1;

            TowerIcon icon = new TowerIcon(i);
            icon.setTranslateX(10 + i * 100);
            icon.setTranslateY(500);
            icon.setOnMouseClicked(e -> {
                selectedColor = icon.getColor();
                selectedIndex = index;
            });
            if (index == 1) {
                selectedColor = icon.getColor();
            }

            getGameScene().addUINode(icon);
        }
    }

    private void initializeLevelLabel() {
        levelLabel = new Label("Level " + (Level.getActiveLevel().getIndex() + 1));
        levelLabel.setStyle("-fx-font-weight: bold");
        levelLabel.setTextFill(Color.WHITE);
        levelLabel.setLayoutY(5);
    }

    private void initializeHpLabel() {
        hpLabel = new Label(Player.getHp() + " HP");
        hpLabel.setTextFill(Color.WHITE);
        hpLabel.setLayoutY(25);
    }

    private void initializeMoneyLabel() {
        moneyLabel = new Label("$" + Player.getMoney());
        moneyLabel.setTextFill(Color.WHITE);
        moneyLabel.setLayoutY(45);
    }

    private void initializeStartCombatButton() {
        startCombatButton = new Button("Start Combat");
        startCombatButton.setOnMousePressed(event -> startCombat());
        startCombatButton.setLayoutY(65);
    }

    private void startCombat() {
        Combat.setCombatStarted(true);
        Combat.setCombatStartedTimestamp(getGameTimer().getNow());
        startCombatButton.setDisable(true);
    }

    private void initializeBackground() {
        labelsBackground = new Rectangle(160, 80, Color.BLACK);
    }

    public static void updateLabels() {
        moneyLabel.setText("$" + Player.getMoney());
        hpLabel.setText(Player.getHp() + " HP");

        if (!Combat.isCombatStarted() && startCombatButton.isDisabled()) {
            startCombatButton.setDisable(false);
        }
    }

    public static Color getSelectedColor() {
        return selectedColor;
    }

    public static void setSelectedColor(Color selectedColor) {
        GamePanel.selectedColor = selectedColor;
    }

    public static int getSelectedIndex() {
        return selectedIndex;
    }

    public static void setSelectedIndex(int selectedIndex) {
        GamePanel.selectedIndex = selectedIndex;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static void setInitialized(boolean initialized) {
        GamePanel.initialized = initialized;
    }
}
