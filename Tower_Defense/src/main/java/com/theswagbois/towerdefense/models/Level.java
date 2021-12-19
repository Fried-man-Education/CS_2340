package com.theswagbois.towerdefense.models;

import javafx.geometry.Point2D;
import java.util.List;

public class Level {
    private static Level activeLevel;

    private Point2D spawnPoint;
    private List<Point2D> waypoints;
    private final int index;
    private final int numEnemies;
    private final double enemySpawnDelay;

    public Level(Point2D spawnPoint, List<Point2D> points, int index,
                 int numEnemies, double enemySpawnDelay) {
        this.spawnPoint = spawnPoint;
        this.waypoints = points;
        this.index = index;
        this.numEnemies = numEnemies;
        this.enemySpawnDelay = enemySpawnDelay;
    }

    public Point2D getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(Point2D spawnPoint) {
        if (spawnPoint == null) {
            throw new IllegalArgumentException("spawn point cannot be null");
        }
        this.spawnPoint = spawnPoint;
    }

    public List<Point2D> getWayPoints() {
        return waypoints;
    }

    public double getEnemySpawnDelay() {
        return enemySpawnDelay;
    }

    public void setWayPoints(List<Point2D> waypoints) {
        if (waypoints == null) {
            throw new IllegalArgumentException("waypoints cannot be null");
        } else if (waypoints.isEmpty()) {
            throw new IllegalArgumentException("waypoints cannot be empty");
        }
        this.waypoints = waypoints;
    }

    public static Level getActiveLevel() {
        return activeLevel;
    }

    public static void setActiveLevel(Level activeLevel) {
        if (activeLevel == null) {
            throw new IllegalArgumentException("active level cannot be null");
        }
        Level.activeLevel = activeLevel;
    }

    public int getIndex() {
        return index;
    }

    public int getNumEnemies() {
        return numEnemies;
    }



    @Override
    public String toString() {
        return "Level{"
                + "spawnPoint=" + spawnPoint
                + ", waypoints=" + waypoints
                + ", index=" + index
                + ", numEnemies=" + numEnemies
                + '}';
    }
}
