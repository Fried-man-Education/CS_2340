package com.theswagbois.towerdefense;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.theswagbois.towerdefense.collision.BulletEnemyHandler;
import com.theswagbois.towerdefense.collision.EnemyMonumentHandler;
import com.theswagbois.towerdefense.collision.PathTowerHandler;
import com.theswagbois.towerdefense.collision.TowerTowerHandler;
import com.theswagbois.towerdefense.entities.Spawn;
import com.theswagbois.towerdefense.entities.TowerDefenseFactory;
import com.theswagbois.towerdefense.event.EnemyKilledEvent;
import com.theswagbois.towerdefense.event.EnemyReachedMonumentEvent;
import com.theswagbois.towerdefense.event.EventHandlers;
import com.theswagbois.towerdefense.event.IllegalTowerLocationEvent;
import com.theswagbois.towerdefense.services.Enemies;
import com.theswagbois.towerdefense.services.Levels;
import com.theswagbois.towerdefense.services.Towers;
import com.theswagbois.towerdefense.ui.GamePanel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import com.theswagbois.towerdefense.ui.MySceneFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

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
        vars.put("numEnemies", 0);
    }

    @Override
    protected void initGame() {
        Towers.loadTowersData();
        Levels.loadLevelData();
        Enemies.loadEnemiesData();
        getGameScene().setCursor(Cursor.DEFAULT);

        getGameWorld().addEntityFactory(new TowerDefenseFactory());
        Levels.initializeLevel(0);

        BooleanProperty enemiesLeft = new SimpleBooleanProperty();
        enemiesLeft.bind(getip("numEnemies").greaterThan(-1));

        getGameTimer().runAtIntervalWhile(Spawn::spawnEnemy, Duration.seconds(2), enemiesLeft);

        getEventBus().addEventHandler(EnemyKilledEvent.ANY, EventHandlers::handleEnemyKilled);
        getEventBus().addEventHandler(
                EnemyReachedMonumentEvent.ANY, EventHandlers::handleEnemyReachedMonument);
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
        getPhysicsWorld().addCollisionHandler(new TowerTowerHandler());
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
