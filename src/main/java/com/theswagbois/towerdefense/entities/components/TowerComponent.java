package com.theswagbois.towerdefense.entities.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import com.theswagbois.towerdefense.entities.TowerDefenseType;
import com.theswagbois.towerdefense.models.Tower;
import com.theswagbois.towerdefense.services.Towers;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class TowerComponent extends Component {

    private int damage;
    private int hp;
    private double attackDelay;
    private double accuracy;
    private double bulletSpeed;
    private LocalTimer shootTimer;
    private double accuracyError = 1;
    private double range;
    private int cost;

    public TowerComponent(int index, int cost) {
        Tower t = Towers.getTowersData().get(index - 1);
        this.cost = cost;
        this.damage = t.getDamage();
        this.hp = t.getHp();
        this.attackDelay = t.getAttackDelay();
        this.accuracy = t.getAccuracy();
        this.bulletSpeed = t.getBulletSpeed();
        this.range = t.getRange();
    }

    @Override
    public void onAdded() {
        shootTimer = FXGL.newLocalTimer();
        shootTimer.capture();
    }

    @Override
    public void onUpdate(double tpf) {

        if (shootTimer.elapsed(Duration.seconds(attackDelay))) {
            FXGL.getGameWorld()
                    .getClosestEntity(entity, e -> e.isType(TowerDefenseType.ENEMY))
                    .ifPresent(nearestEnemy -> {
                        shoot(nearestEnemy);
                        shootTimer.capture();
                    });
        }
    }

    private void shoot(Entity enemy) {
        double bulletSpeed = this.bulletSpeed;

        EnemyComponent enemyProjectile = enemy.getComponent(EnemyComponent.class);
        Point2D position = getEntity().getPosition();
        Point2D enemyPosition = enemy.getPosition();

        if (position.distance(enemyPosition) > this.range) {
            // don't shoot if the enemy is out of range
            return;
        }

        accuracyError = accuracyError
                + FXGLMath.random(-(1 - accuracy), 1 - accuracy);
        Point2D enemyVelocity = enemyProjectile.getVelocity()
                .multiply(bulletSpeed / 5).multiply(accuracyError);

        double a = enemyVelocity.getX() * enemyVelocity.getX()
                + enemyVelocity.getY() * enemyVelocity.getY()
                - bulletSpeed * bulletSpeed;
        double b = 2
                * (enemyVelocity.getX() * (enemyPosition.getX() - position.getX())
                    + enemyPosition.getY() * (enemyPosition.getY() - position.getY()));
        double c = (enemyPosition.getX() - position.getX())
                * (enemyPosition.getX() - position.getX())
                + (enemyPosition.getY() - position.getY())
                * (enemyPosition.getY() - position.getY());
        double disc = b * b - 4 * a * c;
        double t1 = (-b + Math.sqrt(disc)) / (2 * a);
        double t2 = (-b - Math.sqrt(disc)) / (2 * a);
        double t;
        if (t1 < 0) {
            t = t2;
        } else if (t2 < 0) {
            t = t1;
        } else {
            t = Math.min(t1, t2);
        }

        double aimX = t * enemyVelocity.getX() + enemyPosition.getX();
        double aimY = t * enemyVelocity.getY() + enemyPosition.getY();
        Point2D aim = new Point2D(aimX, aimY);

        Entity bullet = FXGL.spawn("Bullet", position);
        bullet.addComponent(
                new ProjectileComponent(aim.subtract(position).normalize(), bulletSpeed)
        );
        bullet.addComponent(new BulletComponent(damage));
    }

    public int getCost() {
        return cost;
    }

    public void upgrade() {
        this.damage *= 2;
        this.attackDelay *= .75;
        this.accuracy = (1 - accuracy) / 3 + accuracy;
        this.bulletSpeed *= 1.5;
        this.range *= 1.5;
        this.cost *= 2;
    }
}
