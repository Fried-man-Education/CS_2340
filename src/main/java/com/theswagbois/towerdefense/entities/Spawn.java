package com.theswagbois.towerdefense.entities;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.theswagbois.towerdefense.models.*;
import com.theswagbois.towerdefense.services.Enemies;
import com.theswagbois.towerdefense.services.Towers;
import com.theswagbois.towerdefense.ui.GamePanel;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class Spawn {

    private static boolean spawnEnemies = false;

    public static void startSpawningEnemies() {
        spawnEnemies = true;
        spawnEnemyTimer();
    }

    public static void stopSpawningEnemies() {
        spawnEnemies = false;
    }

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
            Enemy e = Enemies.getEnemiesData().get(
                    FXGLMath.random(0, Enemies.getEnemiesData().size() - 1)
            );

            int width = (int) (Math.sqrt(e.getHp()) + 10);
            int height = (int) (Math.sqrt(e.getHp()) + 10);

            spawn("Enemy",
                    new SpawnData(Level.getActiveLevel().getSpawnPoint().getX() - width / 2.0,
                            Level.getActiveLevel().getSpawnPoint().getY() - height / 2.0)
                            .put("enemy", e)
                            .put("width", width)
                            .put("height", height)
            );
        }
    }

    public static void spawnEnemyTimer() {
        if (!spawnEnemies || Enemies.getEnemiesData().size() == 0) {
            return;
        }
        spawnEnemy();
        getGameTimer().runOnceAfter(
                Spawn::spawnEnemyTimer, Duration.seconds(
                        Level.getActiveLevel().getEnemySpawnDelay()
                )
        );
    }

    public static void spawnBoss() {
        Enemy e = Boss.getBoss();

        int width = (int) (Math.sqrt(e.getHp()) + 10);
        int height = (int) (Math.sqrt(e.getHp()) + 10);

        spawn("Enemy",
                new SpawnData(Level.getActiveLevel().getSpawnPoint().getX() - width / 2.0,
                        Level.getActiveLevel().getSpawnPoint().getY() - height / 2.0)
                        .put("enemy", e)
                        .put("width", width)
                        .put("height", height)
        );
    }

    public static void spawnTower() {
        Tower selectedTower = Towers.getTowersData().get(GamePanel.getSelectedIndex() - 1);
        if (Player.getMoney() >= selectedTower.getCost()) {
            Player.incrementMoney(-selectedTower.getCost());
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


