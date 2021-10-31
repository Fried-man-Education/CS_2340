package com.theswagbois.towerdefense.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import com.theswagbois.towerdefense.MainApplication;

import java.util.List;

public class EnemyComponent extends Component {

    private List<Point2D> waypoints;
    private Point2D nextWaypoint;

    private double speed;
    private int hp;
    private Rectangle graphic;
    private Point2D velocity;
    private boolean hasReachedGoal;

    public EnemyComponent(int hp, Rectangle graphic) {
        this.hp = hp;
        this.graphic = graphic;
        this.velocity = new Point2D(0, 0);
        this.speed = 2.0 / Math.sqrt(hp / 10.0);
    }

    @Override
    public void onAdded() {
        //waypoints = ((MainApplication) FXGL.getApp()).getWaypoints();

        nextWaypoint = waypoints.remove(0);
    }
}
