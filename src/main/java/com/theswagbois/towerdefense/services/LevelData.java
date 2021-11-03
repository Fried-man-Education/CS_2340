package com.theswagbois.towerdefense.services;

import com.theswagbois.towerdefense.models.Level;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class LevelData {

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
            Level level = new Level(spawnPoint, waypoints);
            LEVELS.add(level);
        }
    }
}
