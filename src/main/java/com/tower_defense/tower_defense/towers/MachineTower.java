package com.tower_defense.tower_defense.towers;

import com.tower_defense.tower_defense.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MachineTower extends AbstractTower {
    static final Color COLOR = (Color) GameController.COLORS[1];
    static final Rectangle GRAPHIC = new Rectangle();

    public MachineTower(int cost) {
        super(GRAPHIC, COLOR, cost);
    }

    @Override
    public void shoot() {
        System.out.println("Machine tower shoots");
    }
}
