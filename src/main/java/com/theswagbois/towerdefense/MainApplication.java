package com.theswagbois.towerdefense;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.theswagbois.towerdefense.collision.BulletEnemyHandler;
import com.theswagbois.towerdefense.collision.EnemyMonumentHandler;
import com.theswagbois.towerdefense.collision.PathTowerHandler;
import com.theswagbois.towerdefense.entities.Spawn;
import com.theswagbois.towerdefense.entities.TowerDefenseFactory;
import com.theswagbois.towerdefense.event.EnemyKilledEvent;
import com.theswagbois.towerdefense.event.EnemyReachedMonumentEvent;
import com.theswagbois.towerdefense.event.EventHandlers;
import com.theswagbois.towerdefense.event.IllegalTowerLocationEvent;
import com.theswagbois.towerdefense.models.Level;
import com.theswagbois.towerdefense.services.LevelData;
import com.theswagbois.towerdefense.services.TowerData;
import com.theswagbois.towerdefense.ui.MySceneFactory;
import com.theswagbois.towerdefense.ui.gamePanel.GamePanel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.theswagbois.towerdefense.entities.Spawn.spawnMonument;
import static com.theswagbois.towerdefense.entities.Spawn.spawnPath;

public class MainApplication extends GameApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("Tower Defense");
        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setIntroEnabled(false);
        gameSettings.setCloseConfirmation(false);
        gameSettings.setManualResizeEnabled(false);
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setSceneFactory(new MySceneFactory());
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Place Tower") {
            private final Rectangle2D worldBounds = new Rectangle2D(
                    0,
                    0,
                    getAppWidth(),
                    getAppHeight() - 100 - 40
            );

            @Override
            protected void onActionBegin() {
                if (worldBounds.contains(input.getMousePositionWorld())) {
                    Spawn.spawnTower();
                }
            }
        }, MouseButton.PRIMARY);

        super.initInput();
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("numEnemies", 100);
    }

    @Override
    protected void initGame() {
        TowerData.loadTowersData();
        LevelData.loadLevelData();
        getGameScene().setCursor(Cursor.DEFAULT);

        getGameWorld().addEntityFactory(new TowerDefenseFactory());

        Level.setActiveLevel(LevelData.getLevels().get(0));
        Point2D enemySpawnPoint = Level.getActiveLevel().getSpawnPoint();
        List<Point2D> waypoints = Level.getActiveLevel().getWaypoints();

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


        BooleanProperty enemiesLeft = new SimpleBooleanProperty();
        enemiesLeft.bind(getip("numEnemies").greaterThan(0));

        getGameTimer().runAtIntervalWhile(Spawn::spawnEnemy, Duration.seconds(2), enemiesLeft);

        getEventBus().addEventHandler(EnemyKilledEvent.ANY, EventHandlers::handleEnemyKilled);
        getEventBus().addEventHandler(EnemyReachedMonumentEvent.ANY, EventHandlers::handleEnemyReachedMonument);
        getEventBus().addEventHandler(
                IllegalTowerLocationEvent.ANY, EventHandlers::handleIllegalTowerLocation
        );

        super.initGame();
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new BulletEnemyHandler());
        getPhysicsWorld().addCollisionHandler(new PathTowerHandler());
        getPhysicsWorld().addCollisionHandler(new EnemyMonumentHandler());
    }


    @Override
    protected void initUI() {
        // black UI background
        Rectangle uiBG = new Rectangle(getAppWidth(), 100);
        uiBG.setTranslateY(500);
        getGameScene().addUINode(uiBG);

        // tower icons, hp and money labels, and start combat button
        getGameScene().addUINode(new GamePanel());
    }


}
