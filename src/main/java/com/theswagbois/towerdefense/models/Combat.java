package com.theswagbois.towerdefense.models;

public class Combat {
    private static boolean combatStarted = false;

    public static boolean isCombatStarted() {
        return combatStarted;
    }

    public static void setCombatStarted(boolean combatStarted) {
        Combat.combatStarted = combatStarted;
    }
}
