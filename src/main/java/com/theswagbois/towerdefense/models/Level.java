package com.theswagbois.towerdefense.models;

import javafx.geometry.Point2D;

import java.util.List;

public class Level {
    private static Level activeLevel;

    private Point2D spawnPoint;
    private List<Point2D> waypoints;
    private int index;

    public Level(Point2D spawnPoint, List<Point2D> points, int index) {
        this.spawnPoint = spawnPoint;
        this.waypoints = points;
        this.index = index;
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

    public List<Point2D> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Point2D> waypoints) {
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
}