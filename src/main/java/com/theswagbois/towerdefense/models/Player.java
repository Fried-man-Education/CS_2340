package com.theswagbois.towerdefense.models;

public class Player {
    private static String name;
    private static String difficulty;
    private static int money;
    private static int hp;

    private static final int BASEMONEY = 1000;
    private static final int BASEHP = 200;

    private static int lastExpense = 0;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        Player.name = name;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty cannot be null");
        }
        Player.difficulty = difficulty;
        calculateMoneyAndHp();
    }

    private static void calculateMoneyAndHp() {
        int newHP;
        int newMoney;
        switch (difficulty) {
        case "Easy":
            newHP = BASEHP;
            newMoney = BASEMONEY;
            break;
        case "Medium":
            newHP = BASEHP / 2;
            newMoney = BASEMONEY / 2;
            break;
        case "Hard":
            newHP = BASEHP / 4;
            newMoney = BASEMONEY / 4;
            break;
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
        if (amount >= 0) {
            lastExpense = amount;
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

    public static int getLastExpense() {
        return lastExpense;
    }

    public static void setLastExpense(int lastExpense) {
        Player.lastExpense = lastExpense;
    }
}
