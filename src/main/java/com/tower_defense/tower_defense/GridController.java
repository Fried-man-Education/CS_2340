package com.tower_defense.tower_defense;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridController extends GridPane {
    public GridController () {
        this.setPrefHeight(600);
        this.setPrefWidth(340);

        int numRows = 17;
        int numCols = 25;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (isPath(col, row)) {
                    this.add(createGrayTile(row, col), col, row);
                } else if (isBase(col, row)) {
                    this.add(createBaseTile(row, col), col, row);
                } else {
                    this.add(createGreenTile(row, col), col, row);
                }
            }
        }
    }

    public boolean isPath(int x, int y) {
        if (x == 2 && y > 11) {
            return true;
        } else if (y == 11 && x < 21 && x >= 2) {
            return true;
        } else {
            return x == 21 && y >= 4 && y <= 11;
        }
    }

    public boolean isBase(int x, int y) {
        return y >= 1 && y <= 3 && x >= 20 && x <= 22;
    }

    public static Rectangle createGrayTile(int row, int col) {
        Rectangle graySquare = new Rectangle();
        graySquare.setId("" + row + "," + col);
        graySquare.setHeight(20);
        graySquare.setWidth(24);
        graySquare.setFill(Color.GREY);
        return graySquare;
    }

    public static Rectangle createGreenTile(int row, int col) {
        Rectangle greenSquare = new Rectangle();
        greenSquare.setId(row + "," + col);
        greenSquare.setHeight(20);
        greenSquare.setWidth(24);
        greenSquare.setFill(Color.GREEN);
        return greenSquare;
    }

    public static Rectangle createBaseTile(int row, int col) {
        Rectangle base = new Rectangle();
        base.setId(row + "," + col);
        base.setHeight(20);
        base.setWidth(24);
        base.setFill(Color.BLUE);
        return base;
    }
}
