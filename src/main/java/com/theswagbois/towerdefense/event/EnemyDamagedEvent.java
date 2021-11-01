package com.theswagbois.towerdefense.event;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.theswagbois.towerdefense.components.BulletComponent;
import com.theswagbois.towerdefense.components.EnemyComponent;
import javafx.event.Event;
import javafx.event.EventType;

public class EnemyDamagedEvent extends Event {

    public static final EventType<EnemyDamagedEvent> ANY
            = new EventType<>(Event.ANY, "ENEMY_DAMAGED");

    private Entity enemy;
    private int damage;

    /**
     * @return damaged enemy
     */
    public Entity getEnemy() {
        return enemy;
    }

    public int getDamage() {
        return damage;
    }

    public EnemyDamagedEvent(Entity enemy, int damage, Entity bullet) {
        super(ANY);
        this.enemy = enemy;
        this.damage = damage;

        EnemyComponent enemyComponent = this.enemy.getComponent(EnemyComponent.class);
        int newHealth = enemyComponent.getHp() - damage;
        enemyComponent.setHp(newHealth);
        System.out.println(newHealth);
        if (newHealth <= 0) {
            FXGL.getEventBus().fireEvent(new EnemyKilledEvent(enemy));
            if (newHealth <= -10) {
                int remainingDamage = -newHealth;
                BulletComponent bc = bullet.getComponent(BulletComponent.class);
                bc.setDamage(remainingDamage);
                ProjectileComponent pc = bullet.getComponent(ProjectileComponent.class);
                pc.setSpeed(pc.getSpeed() * ((double) remainingDamage / damage));
            } else {
                bullet.removeFromWorld();
            }
        } else {
            bullet.removeFromWorld();
        }
    }
}
