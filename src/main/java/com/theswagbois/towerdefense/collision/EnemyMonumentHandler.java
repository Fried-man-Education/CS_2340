package com.theswagbois.towerdefense.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.theswagbois.towerdefense.TowerDefenseType;
import com.theswagbois.towerdefense.components.TowerComponent;
import com.theswagbois.towerdefense.event.EnemyReachedGoalEvent;
import com.theswagbois.towerdefense.event.IllegalTowerLocationEvent;

public class EnemyMonumentHandler extends CollisionHandler {
    public EnemyMonumentHandler() {
        super(TowerDefenseType.ENEMY, TowerDefenseType.MONUMENT);
    }

    @Override
    protected void onCollisionBegin(Entity enemy, Entity monument) {
        enemy.removeFromWorld();
        FXGL.getEventBus().fireEvent(new EnemyReachedGoalEvent());
    }
}
