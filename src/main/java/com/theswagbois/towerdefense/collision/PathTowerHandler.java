package com.theswagbois.towerdefense.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.theswagbois.towerdefense.TowerDefenseType;
import com.theswagbois.towerdefense.entities.components.TowerComponent;
import com.theswagbois.towerdefense.event.IllegalTowerLocationEvent;

public class PathTowerHandler extends CollisionHandler {

    public PathTowerHandler() {
        super(TowerDefenseType.TOWER, TowerDefenseType.PATH);
    }

    @Override
    protected void onCollisionBegin(Entity tower, Entity path) {
        TowerComponent tc = tower.getComponent(TowerComponent.class);

        FXGL.getEventBus().fireEvent(new IllegalTowerLocationEvent(tower));
    }
}
