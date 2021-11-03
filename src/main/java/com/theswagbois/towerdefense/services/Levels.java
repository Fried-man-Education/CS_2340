package com.theswagbois.towerdefense.services;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.theswagbois.towerdefense.models.Level;
import com.theswagbois.towerdefense.models.Player;
import com.theswagbois.towerdefense.ui.GamePanel;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.theswagbois.towerdefense.entities.Spawn.spawnMonument;
import static com.theswagbois.towerdefense.entities.Spawn.spawnPath;

public class Levels {

    private static final List<Level> LEVELS = new ArrayList<Level>();

    public static List<Level> getLevels() {
        return LEVELS;
    }

    public static void loadLevelData() {
        int levelCount = 1;
        for (int i = 1; i <= levelCount; i++) {
            HashMap<String, Object> levelData =
                    getAssetLoader().loadJSON(
                            "levels/level" + i + ".json",
                            HashMap.class
                    ).get();
            int pointsCount = levelData.size();
            Point2D spawnPoint = new Point2D(0, 0);
            List<Point2D> waypoints = new ArrayList<Point2D>();
            for (int j = 0; j < pointsCount; j++) {
                HashMap<String, Object> pointData =
                        (HashMap<String, Object>) levelData.get(String.valueOf(j));
                int x = (Integer) pointData.get("x");
                int y = (Integer) pointData.get("y");
                Point2D point = new Point2D(x, y);
                if (j == 0) {
                    spawnPoint = point;
                } else {
                    waypoints.add(point);
                }
            }
            Level level = new Level(spawnPoint, waypoints, i - 1);
            LEVELS.add(level);
        }
    }

    public static void initializeLevel(int index) {
        // FXGL.getGameWorld().reset();
        ArrayList<Entity> entities = (ArrayList<Entity>) getGameWorld().getEntities().clone();
        FXGL.getGameWorld().removeEntities(entities);

        Level.setActiveLevel(LEVELS.get(index));
        Point2D enemySpawnPoint = Level.getActiveLevel().getSpawnPoint();
        List<Point2D> waypoints = Level.getActiveLevel().getWaypoints();

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

        if (GamePanel.isInitialized()) {
            GamePanel.updateLabels();
        }
    }

    public static void retryLevel() {
        initializeLevel(Level.getActiveLevel().getIndex());
    }
}
