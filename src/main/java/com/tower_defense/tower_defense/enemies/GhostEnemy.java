package com.tower_defense.tower_defense.enemies;

import com.tower_defense.tower_defense.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GhostEnemy extends AbstractEnemy {
    public GhostEnemy(){
        super(new Rectangle(), (Color) GameController.ENEMY_COLORS[0], 100);
    }
}

