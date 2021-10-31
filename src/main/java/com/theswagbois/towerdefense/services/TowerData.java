package com.theswagbois.towerdefense.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class TowerData {
    private final static List<TowerData> towersData = new ArrayList<>();
    public final static int numTowers = 3;

    public static List<TowerData> getTowersData() {
        return towersData;
    }

    public static void loadTowersData() {
        for (int i = 1; i <= numTowers; i++) {
            HashMap<String, Object> towerData = getAssetLoader().loadJSON("tower" + i + ".json", HashMap.class).get();
            String name = (String) towerData.get("name");
            String color = (String) towerData.get("color");
            int damage = (Integer) towerData.get("damage");
            int hp = (Integer) towerData.get("hp");
            double attackDelay = (Double) towerData.get("attackDelay");
            int cost = (Integer) towerData.get("cost");
            double accuracy = (Double) towerData.get("accuracy");
            double bulletSpeed = (Double) towerData.get("bulletSpeed");
            TowerData td = new TowerData(name, color, i, damage, hp, attackDelay, cost, accuracy, bulletSpeed);
            towersData.add(td);
        }
    }

    private String name;
    private String color;
    private int index;
    private int damage;
    private int hp;
    private double attackDelay;
    private int cost;
    private double accuracy;
    private double bulletSpeed;

    public TowerData(String name, String color, int index, int damage, int hp, double attackDelay, int cost, double accuracy, double bulletSpeed) {
        this.name = name;
        this.color = color;
        this.index = index;
        this.damage = damage;
        this.hp = hp;
        this.attackDelay = attackDelay;
        this.cost = cost;
        this.accuracy = accuracy;
        this.bulletSpeed = bulletSpeed;
    }

    public static int getNumTowers() {
        return numTowers;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getAttackDelay() {
        return attackDelay;
    }

    public void setAttackDelay(double attackDelay) {
        this.attackDelay = attackDelay;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(double bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }
}
