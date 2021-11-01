package com.theswagbois.towerdefense;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.theswagbois.towerdefense.collision.BulletEnemyHandler;
import com.theswagbois.towerdefense.event.EnemyKilledEvent;
import com.theswagbois.towerdefense.event.EnemyReachedGoalEvent;
import com.theswagbois.towerdefense.services.TowerData;
import com.theswagbois.towerdefense.ui.TowerIcon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MainApplication extends GameApplication {

    private int levelEnemies = 10000;

    private Point2D enemySpawnPoint = new Point2D(50, 0);

    private List<Point2D> waypoints = new ArrayList<>();

    public List<Point2D> getWaypoints() {
        return new ArrayList<>(waypoints);
    }

    private Label hpLabel;

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
        onKeyDown(KeyCode.F, () -> {
            System.out.println("ouchie");
            //getNotificationService().pushNotification("test");
        });

        Input input = getInput();

        input.addAction(new UserAction("Place Tower") {
            private Rectangle2D worldBounds = new Rectangle2D(0, 0, getAppWidth(), getAppHeight() - 100 - 40);

            @Override
            protected void onActionBegin() {
                if (worldBounds.contains(input.getMousePositionWorld())) {
                    placeTower();
                }
            }
        }, MouseButton.PRIMARY);

        super.initInput();
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("numEnemies", levelEnemies);
    }

    @Override
    protected void initGame() {
        Player.setDifficulty("Easy");
        TowerData.loadTowersData();
        getGameScene().setCursor(Cursor.DEFAULT);

        getGameWorld().addEntityFactory(new TowerDefenseFactory());
        waypoints.addAll(Arrays.asList(
                new Point2D(700, 0),
                new Point2D(700, 300),
                new Point2D(50, 300),
                new Point2D(50, 450),
                new Point2D(700, 500)
        ));

        BooleanProperty enemiesLeft = new SimpleBooleanProperty();
        enemiesLeft.bind(getip("numEnemies").greaterThan(0));

        getGameTimer().runAtIntervalWhile(this::spawnEnemy, Duration.seconds(2), enemiesLeft);

        getEventBus().addEventHandler(EnemyKilledEvent.ANY, this::onEnemyKilled);
        getEventBus().addEventHandler(EnemyReachedGoalEvent.ANY, this::reduceHp);

        super.initGame();
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new BulletEnemyHandler());
    }

    private Color selectedColor;
    private int selectedIndex = 1;

    @Override
    protected void initUI() {
        Rectangle uiBG = new Rectangle(getAppWidth(), 100);
        uiBG.setTranslateY(500);

        getGameScene().addUINode(uiBG);

        for (int i = 0; i < TowerData.numTowers; i++) {
            int index = i + 1;

            TowerIcon icon = new TowerIcon(i);
            icon.setTranslateX(10 + i * 100);
            icon.setTranslateY(500);
            icon.setOnMouseClicked(e -> {
                selectedColor = icon.color;
                selectedIndex = index;
            });
            if (index == 1) {
                this.selectedColor = icon.color;
            }

            getGameScene().addUINode(icon);
        }
        hpLabel = new Label(Player.getHp() + " HP");
        hpLabel.setTextFill(Color.WHITE);
        Rectangle labelsBackground = new Rectangle(160, 80, Color.BLACK);
        Pane labelsPane = new Pane();
        labelsPane.getChildren().add(labelsBackground);
        labelsPane.getChildren().add(hpLabel);
        labelsPane.setTranslateX(510);
        labelsPane.setTranslateY(500);
        getGameScene().addUINode(labelsPane);
    }

    private void spawnEnemy() {
        double secondsElapsed = getGameTimer().getNow();
        inc("numEnemies", -1);

        spawn("Enemy",
                new SpawnData(enemySpawnPoint.getX(), enemySpawnPoint.getY())
                        .put("hp", FXGLMath.random(20, (int) Math.round(40 + secondsElapsed / 2)))
        );
    }

    private void placeTower() {
        spawn("Tower",
                new SpawnData(getInput().getMouseXWorld(), getInput().getMouseYWorld())
                        .put("color", selectedColor)
                        .put("index", selectedIndex)
        );
    }

    private void onEnemyKilled(EnemyKilledEvent event) {
        levelEnemies--;

        System.out.println("Enemy was killed");

        if (levelEnemies == 0) {
            gameOver();
        }

        Entity enemy = event.getEnemy();
        Point2D position = enemy.getPosition();

        Text xMark = getUIFactoryService().newText("X", Color.BLACK, 24);
        xMark.setOpacity(0.2);
        xMark.setTranslateX(position.getX());
        xMark.setTranslateY(position.getY() + 20);


        getGameScene().addGameView(new GameView(xMark, 0));
    }

    private void reduceHp(EnemyReachedGoalEvent event) {
        int decreaseAmount = 10;
        if (Player.getHp() < decreaseAmount) {
            hpLabel.setText("0 HP");
            gameOver();
        } else {
            Player.decreaseHP(10);
            this.hpLabel.setText(Player.getHp() + " HP");
        }
    }



    private void gameOver() {
        showMessage("Demo Over. Thanks for playing!", getGameController()::exit);
    }


}
