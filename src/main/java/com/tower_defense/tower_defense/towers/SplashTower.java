package com.tower_defense.tower_defense.towers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SplashTower extends AbstractTower {
    final static Color color = Color.BLUE;
    final static Rectangle graphic = new Rectangle();
    final static int cost = 100;

    public SplashTower() {
        super(graphic, color, cost);
    }

    @Override
    void shoot() {
        System.out.println("Splash tower shoots");
    }
}
