package com.theswagbois.towerdefense.models;

import javafx.geometry.Point2D;

import java.util.List;

public class Level {
    private static Level activeLevel;

    private Point2D spawnPoint;
    private List<Point2D> waypoints;

    public Level(Point2D spawnPoint, List<Point2D> points) {
        this.spawnPoint = spawnPoint;
        this.waypoints = points;
    }

    public Point2D getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(Point2D spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public List<Point2D> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Point2D> waypoints) {
        this.waypoints = waypoints;
    }

    public static Level getActiveLevel() {
        return activeLevel;
    }

    public static void setActiveLevel(Level activeLevel) {
        Level.activeLevel = activeLevel;
    }
}