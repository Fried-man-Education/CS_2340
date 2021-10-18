package com.tower_defense.tower_defense.towers;

import com.tower_defense.tower_defense.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SplashTower extends AbstractTower {
    final static Color color = (Color) GameController.colors[2];
    final static Rectangle graphic = new Rectangle();
    static int cost;

    public SplashTower(int cost) {
        super(graphic, color, cost);
    }

    @Override
    void shoot() {
        System.out.println("Splash tower shoots");
    }
}
