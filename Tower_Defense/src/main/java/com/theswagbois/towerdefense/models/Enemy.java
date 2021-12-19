package com.theswagbois.towerdefense.models;

public class Enemy {
    private String name;
    private String color;
    private int hp;
    private double speed;

    public Enemy(String name, String color, int hp, double speed) {
        this.name = name;
        this.color = color;
        this.hp = hp;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
