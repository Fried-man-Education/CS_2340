package com.theswagbois.towerdefense.models;

import com.theswagbois.towerdefense.entities.Spawn;

public class Combat {
    private static boolean combatStarted = false;

    private static double combatStartedTimestamp;

    public static boolean isCombatStarted() {
        return combatStarted;
    }

    public static void setCombatStarted(boolean combatStarted) {
        Combat.combatStarted = combatStarted;
        if (combatStarted) {
            Spawn.startSpawningEnemies();
        } else {
            Spawn.stopSpawningEnemies();
        }
    }

    public static double getCombatStartedTimestamp() {
        return combatStartedTimestamp;
    }

    public static void setCombatStartedTimestamp(double combatStartedTimestamp) {
        Combat.combatStartedTimestamp = combatStartedTimestamp;
    }
}
