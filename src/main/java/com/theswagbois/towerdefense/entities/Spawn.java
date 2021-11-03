package com.theswagbois.towerdefense.entities;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.theswagbois.towerdefense.models.Combat;
import com.theswagbois.towerdefense.models.Level;
import com.theswagbois.towerdefense.models.Player;
import com.theswagbois.towerdefense.services.TowerData;
import com.theswagbois.towerdefense.ui.gamePanel.GamePanel;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class Spawn {

    public static void spawnPath(int startX, int endX, int startY, int endY) {
        int thickness = 75;
        int width = Math.abs(endX - startX) + thickness;
        int height = Math.abs(endY - startY) + thickness;
        SpawnData spawnData = new SpawnData(startX - thickness / 2.0, startY - thickness / 2.0);
        spawnData.put("width", width);
        spawnData.put("height", height);
        spawn("Path", spawnData);
    }

    public static void spawnEnemy() {
        if (Combat.isCombatStarted()) {
            double secondsElapsed = getGameTimer().getNow();
            inc("numEnemies", -1);

            int width = 20;
            int height = 20;

            spawn("Enemy",
                    new SpawnData(Level.getActiveLevel().getSpawnPoint().getX() - width / 2.0,
                            Level.getActiveLevel().getSpawnPoint().getY() - height / 2.0)
                            .put("hp",
                                    FXGLMath.random(20, (int) Math.round(40 + secondsElapsed / 2)))
            );
        }
    }

    public static void spawnTower() {
        TowerData selectedTower = TowerData.getTowersData().get(GamePanel.getSelectedIndex() - 1);
        if (Player.getMoney() >= selectedTower.getCost()) {
            Player.decreaseMoney(selectedTower.getCost());
            GamePanel.updateLabels();

            // instantiate tower
            Entity instantiatedTower;
            instantiatedTower = spawn("Tower",
                    new SpawnData(getInput().getMouseXWorld(), getInput().getMouseYWorld())
                            .put("color", GamePanel.getSelectedColor())
                            .put("index", GamePanel.getSelectedIndex())
            );
            // center tower on mouse
            instantiatedTower.setX(instantiatedTower.getX() - instantiatedTower.getWidth() / 2);
            instantiatedTower.setY(instantiatedTower.getY() - instantiatedTower.getHeight() / 2);
        } else {
            showMessage("Not enough money to purchase tower");
        }
    }

    public static void spawnMonument(int x, int y) {
        int width = 50;
        int height = 50;
        spawn("Monument",
                new SpawnData(x - width / 2.0, y - height / 2.0)
                        .put("height", height)
                        .put("width", width)
        );
    }
}


