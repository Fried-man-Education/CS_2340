package com.theswagbois.towerdefense.event;

import com.almasb.fxgl.entity.Entity;
import javafx.event.Event;
import javafx.event.EventType;

public class IllegalTowerLocationEvent extends Event {
    public static final EventType<IllegalTowerLocationEvent> ANY
            = new EventType<>(Event.ANY, "ILLEGAL_TOWER");

    private Entity tower;

    /**
     * @return killed enemy
     */
    public Entity getEnemy() {
        return tower;
    }

    public IllegalTowerLocationEvent(Entity tower) {
        super(ANY);
        System.out.println("Enemy Killed");
        this.tower = tower;
        tower.removeFromWorld();
    }
}
