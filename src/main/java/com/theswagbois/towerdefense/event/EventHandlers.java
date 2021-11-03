package com.theswagbois.towerdefense.event;

import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.entity.Entity;
import com.theswagbois.towerdefense.models.Player;
import com.theswagbois.towerdefense.ui.gamePanel.GamePanel;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;

public class EventHandlers {
    public static void handleEnemyKilled(EnemyKilledEvent event) {
        Entity enemy = event.getEnemy();
        Point2D position = enemy.getPosition();

        // Place x mark where they died
        Text xMark = getUIFactoryService().newText("X", Color.BLACK, 24);
        xMark.setOpacity(0.2);
        xMark.setTranslateX(position.getX());
        xMark.setTranslateY(position.getY() + 20);


        getGameScene().addGameView(new GameView(xMark, 0));
    }

    public static void handleEnemyReachedMonument(EnemyReachedMonumentEvent event) {
        int decreaseAmount = 10;

        if (Player.getHp() <= decreaseAmount) {
            decreaseAmount = Player.getHp();
            showMessage("Game Over. Thanks for playing!", getGameController()::exit);
        }

        Player.decreaseHP(decreaseAmount);
        GamePanel.updateLabels();
    }

    public static void handleIllegalTowerLocation(IllegalTowerLocationEvent event) {
        // refund money
        Player.decreaseMoney(-Player.getLastExpense());
        GamePanel.updateLabels();
        showMessage("You can't place a tower on the path");
    }
}
