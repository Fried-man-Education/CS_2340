package com.theswagbois.towerdefense.event;

import javafx.event.Event;
import javafx.event.EventType;

public class EnemyReachedMonumentEvent extends Event {

    public static final EventType<EnemyReachedMonumentEvent> ANY
            = new EventType<>(Event.ANY, "EnemyReachedGoalEvent");

    public EnemyReachedMonumentEvent() {
        super(ANY);
    }
}
