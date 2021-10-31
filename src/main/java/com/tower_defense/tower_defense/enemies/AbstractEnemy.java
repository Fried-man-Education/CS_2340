package com.tower_defense.tower_defense.enemies;


import com.tower_defense.tower_defense.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.concurrent.TimeUnit;

//// extend this class to create a new type of enemy
//public abstract class AbstractEnemy {
//    private Rectangle graphic;
//    private Color color;
//    private final int speed;
//    private int x;
//    private int y;
//
//    public AbstractEnemy(Rectangle graphic, Color color, int speed) {
//        this.graphic = graphic;
//        this.color = color;
//        this.initializeColor();
//        this.speed = speed;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    // Explode will be the damage the enemy does when it gets to the tower
//    abstract void explode();
//
//    private Rectangle getRectangle() {
//        Rectangle square = new Rectangle();
//        square.setHeight(20);
//        square.setWidth(24);
//        square.setFill(color);
//        return square;
//    }
//
//    // Will replace this with function sending enemies
////
////    public void place(int x, int y) {
////        System.out.println("Placing a tower");
////        this.x = x;
////        this.y = y;
////        // draw the graphic at the specified location
////        GameController.getGrid().add(this.getRectangle(), x, y);
////    }
//
//    private void initializeColor() {
//        if (this.graphic != null && this.color != null) {
//            graphic.setFill(color);
//        } else {
//            System.err.println("Cannot initialize tower color,"
//                    + " color or graphic have not been declared yet");
//        }
//    }
//
//    public Color getColor() {
//        return color;
//    }
//
//    public Rectangle getGraphic() {
//        return graphic;
//    }
//
//}
//import com.tower_defense.tower_defense.GameController;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;

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
        return createGraphic(color);
    }

    private Rectangle createGraphic(Color color) {
        Rectangle square = new Rectangle();
        square.setHeight(20);
        square.setWidth(24);
        square.setFill(color);
        this.graphic = square;
        return square;
    }

    public void move(int newX, int newY){
        System.out.print(x);
        int oldX = this.x;
        int oldY = this.y;
        GameController.getGrid().add(this.createGraphic(), newX, newY);
        Color gridColor = Color.BLUE;
        GameController.getGrid().add(this.createGraphic(gridColor), oldX, oldY);
        this.x = newX;
        this.y = newY;
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
