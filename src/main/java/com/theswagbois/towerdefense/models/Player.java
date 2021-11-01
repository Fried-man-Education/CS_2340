package com.theswagbois.towerdefense.models;

public class Player {
    private static String name;
    private static String difficulty;
    private static int money;
    private static int hp;

    private static final int baseMoney = 1000;
    private static final int baseHp = 200;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        Player.difficulty = difficulty;
        calculateMoneyAndHp();
    }

    private static void calculateMoneyAndHp() {
        int newHP = 0;
        int newMoney = 0;
        switch (difficulty) {
            case "Easy": {
                newHP = baseHp;
                newMoney = baseMoney;
                break;
            }
            case "Medium": {
                newHP = baseHp / 2;
                newMoney = baseMoney / 2;
                break;
            }
            case "Hard": {
                newHP = baseHp / 4;
                newMoney = baseMoney / 4;
                break;
            }
            default: throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        money = newMoney;
        hp = newHP;
    }

    public static int getMoney() {
        return money;
    }

    public static void decreaseMoney(int amount) {
        if (amount > money) {
            throw new IllegalArgumentException("Money cannot be set to a negative number");
        }
        money = money - amount;
    }

    public static int getHp() {
        return hp;
    }

    public static void decreaseHP(int amount) {
        if (amount > hp) {
            throw new IllegalArgumentException("HP cannot be set to a negative number");
        }
        hp = hp - amount;
    }

}
