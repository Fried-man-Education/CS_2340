package com.tower_defense.tower_defense.towers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NormalTower extends AbstractTower {
    final static Color color = Color.RED;
    final static Rectangle graphic = new Rectangle();
    final static int cost = 100;

    public NormalTower() {
        super(graphic, color, cost);
    }

    @Override
    void shoot() {
        System.out.println("Normal tower shoots");
    }
}
