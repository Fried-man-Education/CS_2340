package com.theswagbois.towerdefense.models;

public class Boss extends Enemy {
    private static final String DEFAULTNAME = "Boss";
    private static final String DEFAULTCOLOR = "rgb(100,100,100)";
    private static final int DEFAULTHP = 1000;
    private static final double DEFAULTSPEED = 0.25;

    private Boss(String name, String color, int hp, double speed) {
        super(name, color, hp, speed);
    }

    public static Boss getBoss() {
        return new Boss(DEFAULTNAME, DEFAULTCOLOR, DEFAULTHP, DEFAULTSPEED);
    }
}
