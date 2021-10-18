package com.tower_defense.tower_defense.towers;

import com.tower_defense.tower_defense.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SplashTower extends AbstractTower {
    final static Color color = (Color) GameController.colors[1];
    final static Rectangle graphic = new Rectangle();
    final static int cost = 200;

    public SplashTower() {
        super(graphic, color, cost);
    }

    @Override
    void shoot() {
        System.out.println("Splash tower shoots");
    }
}
