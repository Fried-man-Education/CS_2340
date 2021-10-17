package com.tower_defense.tower_defense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;


public class GameController extends MainApplication {

    private static String difficulty;
    private static String name;
    @FXML private Label moneyLabel;
    @FXML private Label healthLabel;
    @FXML private Pane MapPane;


    @FXML
    public void initialize() {
        moneyLabel.setText("Money: " + difficultMoney(difficulty));
        healthLabel.setText("Health: " + difficultHealth(difficulty));

        GridPane grid = new GridPane();
        grid.setPrefHeight(600);
        grid.setPrefWidth(340);

        int numRows = 17, numCols = 25;
        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numCols; col++){
                if(col == 2 && row > 11){
                    grid.add(createWhiteTile(row, col), col, row);
                } else if(row == 11 && col < 21 && col >= 2){
                    grid.add(createWhiteTile(row, col), col, row);
                } else if(col == 21 && row >= 4 && row <= 11){
                    grid.add(createWhiteTile(row, col), col, row);
                } else if(row >= 1 && row <= 3 && col >= 20 && col <= 22){
                    grid.add(createBaseTile(row, col), col, row);
                } else {
                    grid.add(createGreenTile(row, col), col, row);
                }
            }
        }
        MapPane.getChildren().add(grid);
    }

    public Rectangle createWhiteTile(int row, int col){
        Rectangle whiteSquare = new Rectangle();
        whiteSquare.setId("" + row +","+col);
        whiteSquare.setHeight(20);
        whiteSquare.setWidth(24);
        whiteSquare.setFill(Color.GREY);
        return whiteSquare;
    }

    public Rectangle createGreenTile(int row, int col){
        Rectangle greenSquare = new Rectangle();
        greenSquare.setId("" + row +","+col);
        greenSquare.setHeight(20);
        greenSquare.setWidth(24);
        greenSquare.setFill(Color.GREEN);
        return greenSquare;
    }

    public Rectangle createBaseTile(int row, int col){
        Rectangle base = new Rectangle();
        base.setId(row +","+col);
        base.setHeight(20);
        base.setWidth(24);
        base.setFill(Color.BLUE);
        return base;
    }

    public String difficultMoney(String difficulty) {
        int startingMoney = 1000;
        return calculateValue(difficulty, startingMoney);
    }

    public String difficultHealth(String difficulty) {
        int startingHealth = 200;
        return calculateValue(difficulty, startingHealth);
    }

    private String calculateValue(String difficulty, int num) {
        switch (difficulty) {
        case "Easy":
            return String.valueOf(num);
        case "Medium":
            return String.valueOf(num / 2);
        case "Hard":
            return String.valueOf(num / 4);
        default:
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
    }
//    public void BlueButtonoonAction (ActionEvent actionEvent) throws IOException {
//
//    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        GameController.name = name.trim();
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        GameController.difficulty = difficulty;
    }
}
