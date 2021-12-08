package com.theswagbois.towerdefense.models;

public class Boss extends Enemy {
    static String defaultName = "Boss";
    static String defaultColor = "rgb(100,100,100)";
    static int defaultHP = 1000;
    static double defaultSpeed = 0.25;

    private Boss(String name, String color, int hp, double speed) {
        super(name, color, hp, speed);
    }

    public static Boss getBoss() {
        return new Boss(defaultName, defaultColor, defaultHP, defaultSpeed);
    }
}
