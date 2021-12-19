package com.theswagbois.towerdefense.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.theswagbois.towerdefense.entities.TowerDefenseType;
import com.theswagbois.towerdefense.entities.components.EnemyComponent;
import com.theswagbois.towerdefense.event.EnemyReachedMonumentEvent;

public class EnemyMonumentHandler extends CollisionHandler {
    public EnemyMonumentHandler() {
        super(TowerDefenseType.ENEMY, TowerDefenseType.MONUMENT);
    }

    @Override
    protected void onCollisionBegin(Entity enemy, Entity monument) {
        EnemyComponent ec = enemy.getComponent(EnemyComponent.class);
        int decreaseAmount = 10;

        if (ec.getEnemy().getName().equals("Boss")) {
            decreaseAmount = 10000000;
        }
        enemy.removeFromWorld();
        FXGL.getEventBus().fireEvent(new EnemyReachedMonumentEvent(decreaseAmount));
    }


}
