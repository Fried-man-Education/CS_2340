package com.theswagbois.towerdefense.event;

import com.almasb.fxgl.entity.Entity;
import javafx.event.Event;
import javafx.event.EventType;

public class EnemyReachedGoalEvent extends Event {

    public static final EventType<EnemyReachedGoalEvent> ANY
            = new EventType<>(Event.ANY, "EnemyReachedGoalEvent");

    public EnemyReachedGoalEvent() {
        super(ANY);
    }
}
