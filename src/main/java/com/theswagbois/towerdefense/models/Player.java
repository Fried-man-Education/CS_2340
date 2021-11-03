package com.theswagbois.towerdefense.models;

public class Player {
    private static String name;
    private static String difficulty;
    private static int money;
    private static int hp;

    private static final int BASEMONEY = 1000;
    private static final int BASEHP = 200;

    private static int initialMoney;
    private static int initalHP;

    private static int lastExpense = 0;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        if (!validName(name)) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        Player.name = name.trim();
    }

    public static boolean validName(String name) {
        return name != null && !name.trim().equals("");
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
        switch (difficulty) {
            case "Easy":
                hp = BASEHP;
                money = BASEMONEY;
                break;
            case "Medium":
                hp = BASEHP / 2;
                money = BASEMONEY / 2;
                break;
            case "Hard":
                hp = BASEHP / 4;
                money = BASEMONEY / 4;
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        initalHP = hp;
        initialMoney = money;
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

    public static void resetHP() {
        hp = initalHP;
    }

    public static void resetMoney() {
        money = initialMoney;
    }
}
