package com.theswagbois.towerdefense;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.texture.Texture;
import com.theswagbois.towerdefense.components.EnemyComponent;
import com.theswagbois.towerdefense.components.TowerComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class TowerDefenseFactory implements EntityFactory {
    @Spawns("Enemy")
    public Entity spawnEnemy(SpawnData data) {
        int hp = (Integer) data.getData().get("hp");
        Rectangle enemyGraphic = new Rectangle(Math.sqrt(hp) + 10, Math.sqrt(hp) + 10, Color.RED);
        return entityBuilder(data)
                .type(TowerDefenseType.ENEMY)
                .viewWithBBox(enemyGraphic)
                .with(new CollidableComponent(true))
                .with(new EnemyComponent(hp, enemyGraphic))
                .build();
    }

    @Spawns("Tower")
    public Entity spawnTower(SpawnData data) {
        return entityBuilder(data)
                .type(TowerDefenseType.TOWER)
                .view(new Rectangle(40, 40, data.get("color")))
                .with(new CollidableComponent(true))
                .with(new TowerComponent((Integer) data.getData().get("index")))
                .build();
    }

    @Spawns("Bullet")
    public Entity spawnBullet(SpawnData data) {
        return entityBuilder(data)
                .type(TowerDefenseType.BULLET)
                .viewWithBBox(new Rectangle(15, 5, Color.DARKGREY))
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .build();
    }

    @Spawns("Path")
    public Entity spawnPath(SpawnData data) {
        int width = (Integer) data.getData().get("width");
        int height = (Integer) data.getData().get("height");
        return entityBuilder(data)
                .type(TowerDefenseType.PATH)
                .viewWithBBox(new Rectangle(
                        width,
                        height,
                        Color.LIGHTGRAY
                ))
                .with(new CollidableComponent(true))
                .build();
    }
}
