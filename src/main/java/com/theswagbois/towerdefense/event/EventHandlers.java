package com.theswagbois.towerdefense.event;

import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.entity.Entity;
import com.theswagbois.towerdefense.entities.components.EnemyComponent;
import com.theswagbois.towerdefense.models.Level;
import com.theswagbois.towerdefense.models.Player;
import com.theswagbois.towerdefense.services.Levels;
import com.theswagbois.towerdefense.ui.GamePanel;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;

public class EventHandlers {
    public static void handleEnemyKilled(EnemyKilledEvent event) {
        Entity enemy = event.getEnemy();
        Point2D position = enemy.getPosition();

        EnemyComponent ec = enemy.getComponent(EnemyComponent.class);
        Player.incrementMoney(ec.getMoneyValue());
        GamePanel.updateLabels();

        // Place x mark where they died
        Text xMark = getUIFactoryService().newText("X", Color.BLACK, 24);
        xMark.setOpacity(0.2);
        xMark.setTranslateX(position.getX());
        xMark.setTranslateY(position.getY() + 20);
        xMark.setTranslateZ(5);

        getGameScene().addGameView(new GameView(xMark, 0));

        inc("numEnemies", -1);
        if (getGameWorld().getProperties().getInt("numEnemies") == 0) {
            String winText = "Congratulations! You beat Level "
                    + (Level.getActiveLevel().getIndex() + 1)
                    + "!\nClick OK to go to the next level";
            showMessage(winText, Levels::nextLevel);
        }
    }

    public static void handleEnemyReachedMonument(EnemyReachedMonumentEvent event) {
        int decreaseAmount = 10;

        if (Player.getHp() <= decreaseAmount) {
            decreaseAmount = Player.getHp();
            showMessage("You Lost. Try Again?", Levels::retryLevel);
        }

        Player.decreaseHP(decreaseAmount);
        GamePanel.updateLabels();
    }

    public static void handleIllegalTowerLocation(IllegalTowerLocationEvent event) {
        // refund money
        Player.incrementMoney(Player.getLastExpense());
        GamePanel.updateLabels();
        showMessage("You can't place a tower on the path");
    }
}
