package com.tower_defense.tower_defense;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static javafx.scene.shape.Rectangle.*;

public class TowerController {
    private static String Type;
    public static Rectangle graphic;

    TowerController (String Type) {
        this.Type = Type;
    }

    void Shoot () {
        switch (Type) {
            case "Normal":
                // shoot normally
                break;
            case "Splash":
                // splash normally
                break;
            case "Machine":
                // shoot quickly
        }
    }


    static void setColor (Rectangle rect) {
        switch (Type) {
            case "Normal":
                graphic.setFill(Color.RED);
                break;
            case "Splash":
                graphic.setFill(Color.BLUE);
                break;
            case "Machine":
                graphic.setFill(Color.CHOCOLATE);
        }
    }
}
