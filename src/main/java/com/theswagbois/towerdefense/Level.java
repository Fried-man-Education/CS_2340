package com.theswagbois.towerdefense;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Level {
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
}