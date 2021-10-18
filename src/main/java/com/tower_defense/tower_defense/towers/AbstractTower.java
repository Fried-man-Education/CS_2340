package com.tower_defense.tower_defense.towers;

import com.tower_defense.tower_defense.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// extend this class to create a new type of tower
public abstract class AbstractTower {
    private Rectangle graphic;
    private Color color;
    private final int cost;
    private int x;
    private int y;

    public AbstractTower(Rectangle graphic, Color color, int cost) {
        this.graphic = graphic;
        this.color = color;
        this.cost = cost;
        this.initializeColor();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    abstract void shoot();

    private Rectangle getRectangle() {
        Rectangle square = new Rectangle();
        square.setHeight(20);
        square.setWidth(24);
        square.setFill(color);
        return square;
    }

    public void place(int x, int y) {
        System.out.println("Placing a tower");
        this.x = x;
        this.y = y;
        // draw the graphic at the specified location
        GameController.getGrid().add(this.getRectangle(), x, y);
    }

    private void initializeColor() {
        if (this.graphic != null && this.color != null) {
            graphic.setFill(color);
        } else {
            System.err.println("Cannot initialize tower color,"
                    + " color or graphic have not been declared yet");
        }
    }

    public Color getColor() {
        return color;
    }

    public Rectangle getGraphic() {
        return graphic;
    }

    public int getCost() {
        return cost;
    }
}
