package com.theswagbois.towerdefense.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.theswagbois.towerdefense.models.Player;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;

public class WelcomeMenu extends FXGLMenu {

    private static double defaultButtonWidth;
    private static double defaultButtonHeight;

    private final TextField nameTextField = new TextField();

    public WelcomeMenu() {
        super(MenuType.MAIN_MENU);
        getContentRoot().setCursor(Cursor.DEFAULT); // sets cursor to default
        // adaptive button sizing
        defaultButtonWidth = getAppWidth() / 4.0;
        defaultButtonHeight = getAppHeight() / 15.0;
        setFirstMenu();
    }

    private void setFirstMenu() {
        getContentRoot().getChildren().clear();

        var startButton = new DefaultButton("new game", this::setSecondMenu);
        startButton.setTranslateX(getAppWidth() / 3.0 - defaultButtonWidth / 2.0);
        startButton.setTranslateY(getAppHeight() * 2.0 / 3.0 - defaultButtonHeight / 2.0);

        var exitButton = new DefaultButton("exit", this::closeApp);
        exitButton.setTranslateX(getAppWidth() / 3.0 * 2 - defaultButtonWidth / 2.0);
        exitButton.setTranslateY(startButton.getTranslateY());

        var bg = texture("titleScreen.png", getAppWidth(), getAppHeight());

        getContentRoot().getChildren().addAll(bg, startButton, exitButton);
    }

    private void setSecondMenu() {
        getContentRoot().getChildren().clear();

        final double textFieldWidth = 250;


        nameTextField.setMaxWidth(textFieldWidth);
        nameTextField.setPrefWidth(textFieldWidth);
        nameTextField.setPrefHeight(40);
        nameTextField.setTranslateX(getAppWidth() / 2.0 - textFieldWidth / 2 + 190);
        nameTextField.setTranslateY(getAppHeight() / 3.0 - 60);
        String style = "-fx-control-inner-background: #121212;";
        style += "-fx-focus-color: #850300;"
                + "-fx-border-color: #850300;"
                + "-fx-faint-focus-color: #121212;";
        style += "-fx-text-fill: #850300;";
        nameTextField.setStyle(style);

        var easyButton = new DifficultyButton("Easy", 0);
        var mediumButton = new DifficultyButton("Medium", 50);
        var hardButton = new DifficultyButton("Hard", 100);

        var beginGameButton = new DefaultButton("Start new game", this::startGame);
        beginGameButton.setTranslateX(getAppWidth() / 2.0 - defaultButtonWidth / 2.0);
        beginGameButton.setTranslateY(getAppHeight() / 3.0 + 250);

        var bg = texture("settingsScreen.png", getAppWidth(), getAppHeight());

        getContentRoot().getChildren().addAll(
                bg,
                nameTextField,
                easyButton,
                mediumButton,
                hardButton,
                beginGameButton
        );
    }

    private void startGame() {
        try {
            Player.setName(nameTextField.getText());
            Player.setDifficulty(Player.getDifficulty());
            fireNewGame();
            this.setFirstMenu();
        } catch (Exception ignored) {
            System.out.println("Name or difficulty not yet chosen");
        }
    }

    private void closeApp() {
        Platform.exit();
    }

    private class DifficultyButton extends DefaultButton {

        public DifficultyButton(String name, int xOffset) {
            super(name, () -> Player.setDifficulty(name));
            double buttonWidth = 75;
            setMaxWidth(buttonWidth);
            setTranslateX(getAppWidth() / 2.0 + 113);
            setTranslateY(getAppHeight() / 3.0 - buttonWidth / 2 + xOffset + 35);
        }
    }

    private static class DefaultButton extends StackPane {
        public DefaultButton(String name, Runnable action) {
            var bg = new Rectangle(defaultButtonWidth, defaultButtonHeight);
            bg.setStroke(Paint.valueOf("#850300"));

            var text = getUIFactoryService().newText(name, (Color) Paint.valueOf("#850300"), 18);

            bg.fillProperty().bind(
                    Bindings.when(hoverProperty())
                            .then((Color) Paint.valueOf("#850300"))
                            .otherwise((Color) Paint.valueOf("#121212"))
            );

            text.fillProperty().bind(
                    Bindings.when(hoverProperty())
                            .then(Paint.valueOf("#121212"))
                            .otherwise(Paint.valueOf("#850300"))
            );

            setOnMouseClicked(e -> action.run());


            getChildren().addAll(bg, text);
        }
    }
}