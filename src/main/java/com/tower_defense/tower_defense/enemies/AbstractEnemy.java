package com.tower_defense.tower_defense.enemies;
import com.tower_defense.tower_defense.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class AbstractEnemy {
    private Rectangle graphic;
    private final Color color;
    private int health;
    private int x;
    private int y;

    public AbstractEnemy(Rectangle graphic, Color color, int health) {
        this.graphic = graphic;
        this.color = color;
        this.health = health;
        this.initializeColor();
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    public int getHealth(){
        return health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getGraphic() {
        return graphic;
    }

    private Rectangle createGraphic() {
        Rectangle square = new Rectangle();
        square.setHeight(20);
        square.setWidth(24);
        square.setFill(color);
        this.graphic = square;
        return square;
    }

    public void move(int x, int y){
        this.x = x;
        this.y = y;
        GameController.getGrid().add(this.createGraphic(), x, y);
    }

    private void initializeColor() {
        if (this.graphic != null && this.color != null) {
            graphic.setFill(color);
        } else {
            System.err.println("Cannot initialize tower color,"
                    + " color or graphic have not been declared yet");
        }
    }
}
