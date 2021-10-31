package com.tower_defense.tower_defense.towers;

import com.tower_defense.tower_defense.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NormalTower extends AbstractTower {
    static final Color COLOR = (Color) GameController.TOWER_COLORS[0];
    static final Rectangle GRAPHIC = new Rectangle();

    public NormalTower(int cost) {
        super(GRAPHIC, COLOR, cost);
    }

    @Override
    void shoot() {
        System.out.println("Normal tower shoots");
    }
}
