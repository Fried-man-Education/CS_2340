package com.tower_defense.tower_defense.towers;

import com.tower_defense.tower_defense.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SplashTower extends AbstractTower {
    static final Color COLOR = (Color) GameController.TOWER_COLORS[2];
    static final Rectangle GRAPHIC = new Rectangle();

    public SplashTower(int cost) {
        super(GRAPHIC, COLOR, cost);
    }

    @Override
    void shoot() {
        System.out.println("Splash tower shoots");
    }
}
