package com.theswagbois.towerdefense.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.theswagbois.towerdefense.entities.TowerDefenseType;
import com.theswagbois.towerdefense.entities.components.BulletComponent;
import com.theswagbois.towerdefense.event.EnemyDamagedEvent;

public class BulletEnemyHandler extends CollisionHandler {

    public BulletEnemyHandler() {
        super(TowerDefenseType.BULLET, TowerDefenseType.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity enemy) {
        BulletComponent bc = bullet.getComponent(BulletComponent.class);

        FXGL.getEventBus().fireEvent(new EnemyDamagedEvent(enemy, bc.getDamage(), bullet));
    }
}
