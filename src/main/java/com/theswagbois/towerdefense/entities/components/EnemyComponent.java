package com.theswagbois.towerdefense.entities.components;

import com.almasb.fxgl.entity.component.Component;
import com.theswagbois.towerdefense.models.Level;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class EnemyComponent extends Component {

    private List<Point2D> waypoints;
    private Point2D nextWaypoint;
    private int nextWaypointIndex = 0;

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
        waypoints = Level.getActiveLevel().getWaypoints();

        nextWaypoint = waypoints.get(0);
    }

    @Override
    public void onUpdate(double tpf) {

        velocity = nextWaypoint.subtract(entity.getPosition())
                .normalize()
                .multiply(speed);

        entity.translate(velocity);

        if (nextWaypoint.distance(entity.getPosition()) < speed) {
            entity.setPosition(nextWaypoint);

            if (!waypoints.isEmpty()) {
                nextWaypointIndex++;
                nextWaypoint = waypoints.get(nextWaypointIndex);
            } else {
                if (!hasReachedGoal) {
                    hasReachedGoal = true;
                }

            }
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        this.graphic.setHeight(Math.sqrt(hp) + 10);
        this.graphic.setWidth(Math.sqrt(hp) + 10);
    }

    public Point2D getVelocity() {
        return velocity;
    }
}
