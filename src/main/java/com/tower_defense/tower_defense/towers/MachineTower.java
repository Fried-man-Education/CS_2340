package com.tower_defense.tower_defense.towers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MachineTower extends AbstractTower {
    final static Color color = Color.CHOCOLATE;
    final static Rectangle graphic = new Rectangle();
    final static int cost = 100;

    public MachineTower() {
        super(graphic, color, cost);
    }

    @Override
    void shoot() {
        System.out.println("Machine tower shoots");
    }
}
