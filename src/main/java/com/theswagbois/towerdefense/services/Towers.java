package com.theswagbois.towerdefense.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class Towers {
    private static final List<Towers> TOWERS_DATA = new ArrayList<>();
    public static final int NUMTOWERS = 3;

    public static List<Towers> getTowersData() {
        return TOWERS_DATA;
    }

    public static void loadTowersData() {
        for (int i = 1; i <= NUMTOWERS; i++) {
            HashMap<String, Object> towerData =
                    getAssetLoader().loadJSON(
                            "towers/tower" + i + ".json",
                    HashMap.class
                ).get();
            String name = (String) towerData.get("name");
            String color = (String) towerData.get("color");
            int damage = (Integer) towerData.get("damage");
            int hp = (Integer) towerData.get("hp");
            double attackDelay = (Double) towerData.get("attackDelay");
            int cost = (Integer) towerData.get("cost");
            double accuracy = (Double) towerData.get("accuracy");
            double bulletSpeed = (Double) towerData.get("bulletSpeed");
            int[] intValues = new int[]{i, damage, cost};
            Towers td = new Towers(name, color,  hp,
                    attackDelay, accuracy, bulletSpeed, intValues);
            TOWERS_DATA.add(td);
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

    public Towers(String name, String color, int hp,
                  double attackDelay, double accuracy, double bulletSpeed, int[] intValues) {
        this.name = name;
        this.color = color;
        this.index = intValues[0];
        this.damage = intValues[1];
        this.hp = hp;
        this.attackDelay = attackDelay;
        this.cost = intValues[2];
        this.accuracy = accuracy;
        this.bulletSpeed = bulletSpeed;
    }

    public static int getNumTowers() {
        return NUMTOWERS;
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
