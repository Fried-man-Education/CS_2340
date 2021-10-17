package com.tower_defense.tower_defense.towers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// extend this class to create a new type of tower
public abstract class AbstractTower {

    Rectangle graphic;
    Color color;
    public int cost;
    public int x;
    public int y;

    public AbstractTower(Rectangle graphic, Color color, int cost) {
        this.graphic = graphic;
        this.color = color;
        this.cost = cost;
        this.initializeColor();
    }

    abstract void shoot();

    public void place(int x, int y) {
        System.out.println("Placing a tower");
        this.x = x;
        this.y = y;
        // draw the graphic at the specified location
    }

    private void initializeColor() {
        if (this.graphic != null && this.color != null) {
            graphic.setFill(color);
        } else {
            System.err.println("Cannot initialize tower color, color or graphic have not been declared yet");
        }
    }

}
