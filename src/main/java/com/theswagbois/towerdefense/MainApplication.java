package com.theswagbois.towerdefense;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.theswagbois.towerdefense.collision.BulletEnemyHandler;
import com.theswagbois.towerdefense.collision.EnemyMonumentHandler;
import com.theswagbois.towerdefense.collision.PathTowerHandler;
import com.theswagbois.towerdefense.event.EnemyKilledEvent;
import com.theswagbois.towerdefense.event.EnemyReachedGoalEvent;
import com.theswagbois.towerdefense.event.IllegalTowerLocationEvent;
import com.theswagbois.towerdefense.models.Player;
import com.theswagbois.towerdefense.services.LevelData;
import com.theswagbois.towerdefense.services.TowerData;
import com.theswagbois.towerdefense.ui.TowerIcon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    private Label moneyLabel;
    private Button startCombatButton;
    private Pane labelsPane = new Pane();

    private int lastCost = 0;

    boolean combatStarted = false;

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
        TowerData.loadTowersData();
        LevelData.loadLevelData();
        getGameScene().setCursor(Cursor.DEFAULT);

        getGameWorld().addEntityFactory(new TowerDefenseFactory());

        enemySpawnPoint = LevelData.levels.get(0).getSpawnPoint();
        waypoints = LevelData.levels.get(0).getWaypoints();

        for (int i = 0; i < waypoints.size(); i++) {
            Point2D point1;
            if (i == 0) {
                point1 = enemySpawnPoint;
            } else {
                point1 = waypoints.get(i-1);
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

        getGameTimer().runAtIntervalWhile(this::spawnEnemy, Duration.seconds(2), enemiesLeft);

        getEventBus().addEventHandler(EnemyKilledEvent.ANY, this::onEnemyKilled);
        getEventBus().addEventHandler(EnemyReachedGoalEvent.ANY, this::reduceHp);
        getEventBus().addEventHandler(IllegalTowerLocationEvent.ANY, this::handleIllegalTowerPosition);

        super.initGame();
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new BulletEnemyHandler());
        getPhysicsWorld().addCollisionHandler(new PathTowerHandler());
        getPhysicsWorld().addCollisionHandler(new EnemyMonumentHandler());
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
        moneyLabel = new Label("$" + Player.getMoney());
        moneyLabel.setTextFill(Color.WHITE);
        moneyLabel.setLayoutY(15);
        startCombatButton = new Button("Start Combat");
        startCombatButton.setOnMousePressed(event -> startCombat());
        startCombatButton.setLayoutY(30);
        Rectangle labelsBackground = new Rectangle(160, 80, Color.BLACK);
        labelsPane.getChildren().add(labelsBackground);
        labelsPane.getChildren().add(hpLabel);
        labelsPane.getChildren().add(moneyLabel);
        labelsPane.getChildren().add(startCombatButton);
        labelsPane.setTranslateX(510);
        labelsPane.setTranslateY(500);
        getGameScene().addUINode(labelsPane);
    }

    private void spawnPath(int startX, int endX, int startY, int endY) {
        int thickness = 75;
        int width = Math.abs(endX - startX) + thickness;
        int height = Math.abs(endY - startY) + thickness;
        SpawnData spawnData = new SpawnData(startX - thickness / 2.0, startY - thickness / 2.0);
        spawnData.put("width", width);
        spawnData.put("height", height);
        spawn("Path", spawnData);
    }

    private void spawnEnemy() {
        if (combatStarted) {
            double secondsElapsed = getGameTimer().getNow();
            inc("numEnemies", -1);

            int width = 20;
            int height = 20;

            spawn("Enemy",
                    new SpawnData(enemySpawnPoint.getX() - width / 2.0, enemySpawnPoint.getY() - height / 2.0)
                            .put("hp", FXGLMath.random(20, (int) Math.round(40 + secondsElapsed / 2)))
            );
        }
    }

    private void placeTower() {
        TowerData selectedTower = TowerData.getTowersData().get(selectedIndex - 1);
        lastCost = selectedTower.getCost();
        if (Player.getMoney() >= lastCost) {
            Player.decreaseMoney(lastCost);
            spawn("Tower",
                    new SpawnData(getInput().getMouseXWorld(), getInput().getMouseYWorld())
                            .put("color", selectedColor)
                            .put("index", selectedIndex)
            );
            moneyLabel.setText("$" + Player.getMoney());
        } else {
            showMessage("Not enough money to purchase tower");
        }
    }

    private void spawnMonument(int x, int y) {
        int width = 50;
        int height = 50;
        spawn("Monument",
                new SpawnData(x - width / 2.0, y - height / 2.0)
                        .put("height", height)
                        .put("width", width)
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

    private void handleIllegalTowerPosition(IllegalTowerLocationEvent event) {
        Player.decreaseMoney(-lastCost);
        showMessage("You can't place a tower on the path");
    }

    private void startCombat() {
        combatStarted = true;
        labelsPane.getChildren().remove(startCombatButton);
    }



    private void gameOver() {
        showMessage("Demo Over. Thanks for playing!", getGameController()::exit);
    }


}
