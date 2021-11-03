package com.theswagbois.towerdefense.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.theswagbois.towerdefense.entities.TowerDefenseType;
import com.theswagbois.towerdefense.event.IllegalTowerLocationEvent;

public class TowerTowerHandler extends CollisionHandler {

    public TowerTowerHandler() {
        super(TowerDefenseType.TOWER, TowerDefenseType.TOWER);
    }

    @Override
    protected void onCollisionBegin(Entity tower, Entity tower2) {
        FXGL.getEventBus().fireEvent(new IllegalTowerLocationEvent(tower2));
    }
}
