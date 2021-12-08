package com.theswagbois.towerdefense.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.theswagbois.towerdefense.entities.TowerDefenseType;
import com.theswagbois.towerdefense.entities.components.TowerComponent;
import com.theswagbois.towerdefense.event.IllegalTowerLocationEvent;
import com.theswagbois.towerdefense.models.Player;
import javafx.scene.text.Text;

public class TowerTowerHandler extends CollisionHandler {

    public TowerTowerHandler() {
        super(TowerDefenseType.TOWER, TowerDefenseType.TOWER);
    }

    @Override
    protected void onCollisionBegin(Entity tower, Entity tower2) {
        System.out.println(tower2.getComponent(TowerComponent.class).getCost());
        System.out.println(tower.getComponent(TowerComponent.class).getCost());

        int costDifference = (tower.getComponent(TowerComponent.class).getCost()
                - tower2.getComponent(TowerComponent.class).getCost());

        if (costDifference == 0) {
            tower.getViewComponent().addChild(new Text("+"));
            tower.getComponent(TowerComponent.class).upgrade();
            tower2.removeFromWorld();
            return;
        } else {
            FXGL.getEventBus().fireEvent(new IllegalTowerLocationEvent(tower2));
        }
        Player.incrementMoney((Player.getLastExpense()));
        tower2.removeFromWorld();
    }
}
