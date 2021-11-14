package com.theswagbois.towerdefense.services;

import com.theswagbois.towerdefense.models.Enemy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class Enemies {
    private static final List<Enemy> ENEMIES_DATA = new ArrayList<>();
    private static int numEnemies = 3;

    public static List<Enemy> getEnemiesData() {
        return ENEMIES_DATA;
    }

    public static void loadEnemiesData() {
        for (int i = 1; i <= numEnemies; i++) {
            HashMap<String, Object> enemyData =
                    getAssetLoader().loadJSON(
                            "enemies/enemy" + i + ".json",
                            HashMap.class
                    ).get();
            String name = (String) enemyData.get("name");
            String color = (String) enemyData.get("color");
            int hp = (Integer) enemyData.get("hp");
            double speed = (Double) enemyData.get("speed");
            Enemy e = new Enemy(name, color, hp, speed);
            ENEMIES_DATA.add(e);
        }
    }
    public static int getNumEnemies() {
        return numEnemies;
    }

    public static void setNumEnemies(int num) {
        numEnemies = num;
    }

}
