package com.theswagbois.towerdefense.ui;

import com.theswagbois.towerdefense.models.Tower;
import com.theswagbois.towerdefense.services.Towers;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TowerIcon extends Pane {

    private Color color;

    public TowerIcon(int index) {

        Tower t = Towers.getTowersData().get(index);
        this.color = Color.web(t.getColor());

        Rectangle defaultRect = new Rectangle(80, 80, color);
        Rectangle overlayRect = new Rectangle(80, 80, color);
        overlayRect.setOpacity(0.5);
        getChildren().add(defaultRect);

        Label infoLabel = new Label(
                t.getHp() + " HP\n"
                + t.getDamage() + " Damage\n"
                + t.getAttackDelay() + " s / Shot\n"
                + Math.round(t.getAccuracy() * 100) + "% Accuracy\n$"
                + t.getCost());
        infoLabel.setTextFill(Color.WHITE);

        this.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                getChildren().remove(defaultRect);
                getChildren().add(overlayRect);
                getChildren().add(infoLabel);
            } else {
                getChildren().add(defaultRect);
                getChildren().remove(overlayRect);
                getChildren().remove(infoLabel);
            }
        });
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
