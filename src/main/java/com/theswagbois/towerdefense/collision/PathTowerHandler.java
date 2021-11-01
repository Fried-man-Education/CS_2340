package com.theswagbois.towerdefense.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.theswagbois.towerdefense.TowerDefenseType;
import com.theswagbois.towerdefense.components.BulletComponent;
import com.theswagbois.towerdefense.event.EnemyDamagedEvent;

public class PathTowerHandler extends CollisionHandler {

    public PathTowerHandler() {
        super(TowerDefenseType.BULLET, TowerDefenseType.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity enemy) {
        BulletComponent bc = bullet.getComponent(BulletComponent.class);

        FXGL.getEventBus().fireEvent(new EnemyDamagedEvent(enemy, bc.getDamage(), bullet));
    }
}
