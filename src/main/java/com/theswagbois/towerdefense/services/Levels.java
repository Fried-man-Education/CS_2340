package com.theswagbois.towerdefense.services;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.theswagbois.towerdefense.entities.Spawn;
import com.theswagbois.towerdefense.event.EnemyDamagedEvent;
import com.theswagbois.towerdefense.event.EventHandlers;
import com.theswagbois.towerdefense.models.Combat;
import com.theswagbois.towerdefense.models.Level;
import com.theswagbois.towerdefense.models.Player;
import com.theswagbois.towerdefense.ui.GamePanel;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.set;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.theswagbois.towerdefense.entities.Spawn.spawnMonument;
import static com.theswagbois.towerdefense.entities.Spawn.spawnPath;

public class Levels {

    private static int levelCount = 3;

    private static final List<Level> LEVELS = new ArrayList<Level>();

    public static List<Level> getLevels() {
        return LEVELS;
    }

    public static void loadLevelData() {
        for (int i = 1; i <= levelCount; i++) {
            HashMap<String, Object> levelData =
                    getAssetLoader().loadJSON(
                            "levels/level" + i + ".json",
                            HashMap.class
                    ).get();
            int numEnemies = (Integer) levelData.get("numEnemies");
            HashMap<String, Object> waypointsData =
                    (HashMap<String, Object>) levelData.get("waypoints");
            int pointsCount = waypointsData.size();
            Point2D spawnPoint = new Point2D(0, 0);
            List<Point2D> waypoints = new ArrayList<Point2D>();
            for (int j = 0; j < pointsCount; j++) {
                HashMap<String, Object> pointData =
                        (HashMap<String, Object>) waypointsData.get(String.valueOf(j));
                int x = (Integer) pointData.get("x");
                int y = (Integer) pointData.get("y");
                Point2D point = new Point2D(x, y);
                if (j == 0) {
                    spawnPoint = point;
                } else {
                    waypoints.add(point);
                }
            }
            double enemySpawnDelay = (Double) levelData.get("enemySpawnDelay");
            Level level = new Level(spawnPoint, waypoints, i - 1, numEnemies, enemySpawnDelay);
            LEVELS.add(level);
            System.out.println(LEVELS);
        }
    }

    public static void initializeLevel(int index) {
        // FXGL.getGameWorld().reset();
        ArrayList<Entity> entities = (ArrayList<Entity>) getGameWorld().getEntities().clone();
        FXGL.getGameWorld().removeEntities(entities);

        // remove x-marks
        getGameScene().clearGameViews();

        Level.setActiveLevel(LEVELS.get(index));
        Point2D enemySpawnPoint = Level.getActiveLevel().getSpawnPoint();
        List<Point2D> waypoints = Level.getActiveLevel().getWayPoints();

        // generate path entities
        for (int i = 0; i < waypoints.size(); i++) {
            Point2D point1;
            if (i == 0) {
                point1 = enemySpawnPoint;
            } else {
                point1 = waypoints.get(i - 1);
            }
            Point2D point2 = waypoints.get(i);

            int startX = (int) point1.getX();
            int endX = (int) point2.getX();
            int startY = (int) point1.getY();
            int endY = (int) point2.getY();

            if (endX < startX) {
                int temp = startX;
                startX = endX;
                endX = temp;
            }

            if (endY < startY) {
                int temp = startY;
                startY = endY;
                endY = temp;
            }

            spawnPath(startX, endX, startY, endY);
        }

        Point2D monumentLocation = waypoints.get(waypoints.size() - 1);
        spawnMonument((int) monumentLocation.getX(), (int) monumentLocation.getY());

        Player.resetHP();
        Player.resetMoney();
        Combat.setCombatStarted(false);

        if (GamePanel.isInitialized()) {
            GamePanel.updateLabels();
        }

        set("numEnemies", Level.getActiveLevel().getNumEnemies());
    }

    public static void retryLevel() {
        initializeLevel(Level.getActiveLevel().getIndex());
    }

    public static void nextLevel() {
        int nextIndex = Level.getActiveLevel().getIndex() + 1;
        System.out.println(nextIndex);
        System.out.println(LEVELS.size());


        if (nextIndex >= LEVELS.size()) {
            String message = "Congratulations! You completed every level!"
                    + "\nEnemies killed: " + EventHandlers.getEnemiesKilled()
                    + "\nDamage done: " + EnemyDamagedEvent.getDamageDone()
                    + "\nMoney spent: $" + Spawn.getMoneySpent();
            Button restartGame = new Button("Restart Game");
            restartGame.setOnMouseClicked(e -> Levels.restartGame());

            Button closeGame = new Button("Close Game");
            closeGame.setOnMouseClicked(e -> Platform.exit());
            FXGL.Companion.getDialogService().showBox(message, restartGame, closeGame);
        } else {
            initializeLevel(nextIndex);
        }

    }

    public static void restartGame() {
        EventHandlers.resetEnemiesKilled();
        EnemyDamagedEvent.resetDamageDone();
        Spawn.resetMoneySpent();
        FXGL.getGameController().gotoMainMenu();
        // initializeLevel(0);
    }

    public static int getLevelCount() {
        return levelCount;
    }

    public static void setLevelCount(int levelCount) {
        Levels.levelCount = levelCount;
    }
}
