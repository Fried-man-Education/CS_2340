package com.theswagbois.towerdefense.services;

import com.theswagbois.towerdefense.models.Tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class Towers {
    private static final List<Tower> TOWERS_DATA = new ArrayList<>();
    private static int numTowers = 3;

    public static List<Tower> getTowersData() {
        return TOWERS_DATA;
    }

    public static void loadTowersData() {
        for (int i = 1; i <= numTowers; i++) {
            HashMap<String, Object> towerData =
                    getAssetLoader().loadJSON(
                            "towers/tower" + i + ".json",
                    HashMap.class
                ).get();
            String name = (String) towerData.get("name");
            String color = (String) towerData.get("color");
            int damage = (Integer) towerData.get("damage");
            int hp = (Integer) towerData.get("hp");
            double attackDelay = (Double) towerData.get("attackDelay");
            int cost = (Integer) towerData.get("cost");
            double accuracy = (Double) towerData.get("accuracy");
            double bulletSpeed = (Double) towerData.get("bulletSpeed");
            double range = (Double) towerData.get("range");
            int[] intValues = new int[]{i, damage, cost, hp};
            Tower t = new Tower(name, color, attackDelay, accuracy, bulletSpeed, range, intValues);
            TOWERS_DATA.add(t);
        }
    }
    public static int getNumTowers() {
        return numTowers;
    }

    public static void setNumTowers(int num) {
        numTowers = num;
    }

}
