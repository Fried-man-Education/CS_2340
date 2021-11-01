package com.theswagbois.towerdefense.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.theswagbois.towerdefense.models.Player;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.Console;

import static com.almasb.fxgl.dsl.FXGL.*;

public class WelcomeMenu extends FXGLMenu {

    private final Pane startPane = new Pane();
    private final Pane settingsPane = new Pane();
    private static double defaultButtonWidth;
    private static double defaultButtonHeight;

    private final TextField nameTextField = new TextField();

    public WelcomeMenu() {
        super(MenuType.MAIN_MENU);
        getContentRoot().setCursor(Cursor.DEFAULT); // sets cursor to default
        // adaptive button sizing
        defaultButtonWidth = getAppWidth() / 4;
        defaultButtonHeight = getAppHeight() / 15;
        setFirstMenu();
    }

    private void setFirstMenu() {
        var startButton = new defaultButton("new game", this::setSecondMenu);
        startButton.setTranslateX(getAppWidth() / 3.0 - defaultButtonWidth / 2.0);
        startButton.setTranslateY(getAppHeight() * 2.0 / 3.0 - defaultButtonHeight / 2.0);

        var exitButton = new defaultButton("exit", this::closeApp);
        exitButton.setTranslateX(getAppWidth() / 3.0 * 2 - defaultButtonWidth / 2.0);
        exitButton.setTranslateY(startButton.getTranslateY());

        var bg = texture("titleScreen.png", getAppWidth(), getAppHeight());
        bg.setTranslateY(0);
        bg.setTranslateX(0);
        getContentRoot().getChildren().add(bg);

        getContentRoot().getChildren().add(startButton);
        getContentRoot().getChildren().add(exitButton);
    }

    private void setSecondMenu() {
        getContentRoot().getChildren().clear();

        final double textFieldWidth = 200;

        var title = new Text("Configuration");
        title.setFont(Font.font(30));
        title.setTranslateX(getAppWidth() / 2.0 - title.getLayoutBounds().getWidth() / 2);
        title.setTranslateY(getAppHeight() / 3.0);

        var nameText = new Text("Enter your name");
        nameText.setFont(Font.font(15));
        nameText.setTranslateX(getAppWidth() / 2.0 - nameText.getLayoutBounds().getWidth() / 2);
        nameText.setTranslateY(getAppHeight() / 3.0 + 50);

        nameTextField.setMaxWidth(textFieldWidth);
        nameTextField.setPrefWidth(textFieldWidth);
        nameTextField.setTranslateX(getAppWidth() / 2.0 - textFieldWidth / 2);
        nameTextField.setTranslateY(getAppHeight() / 3.0 + 60);

        var difficultyText = new Text("Select difficulty");
        difficultyText.setFont(Font.font(15));
        difficultyText.setTranslateX(getAppWidth() / 2.0 - difficultyText.getLayoutBounds().getWidth() / 2);
        difficultyText.setTranslateY(getAppHeight() / 3.0 + 150);

        var easyButton = new DifficultyButton("Easy", -100);
        var mediumButton = new DifficultyButton("Medium", 0);
        var hardButton = new DifficultyButton("Hard", 100);

        var beginGameButton = new defaultButton("Start new game", this::startGame);
        beginGameButton.setTranslateX(getAppWidth() / 2.0 - defaultButtonWidth / 2.0);
        beginGameButton.setTranslateY(getAppHeight() / 3.0 + 250);

        getContentRoot().getChildren().addAll(title, nameText, nameTextField, difficultyText, easyButton, mediumButton, hardButton, beginGameButton);
    }

    private void startGame() {
        if (checkValidName(nameTextField.getText()) && Player.getDifficulty() != null) {
            fireNewGame();
        }
    }

    public static boolean checkValidName(String name) {
        return name != null && name.trim().length() > 0;
    }

    private  void closeApp() {
        Platform.exit();
    }

    private class DifficultyButton extends Button {
        final double buttonWidth = 75;
        final double yOffset = 160;

        public DifficultyButton(String name, int xOffset) {
            setText(name);
            setPrefWidth(buttonWidth);
            setMaxWidth(buttonWidth);
            setTranslateX(getAppWidth() / 2.0 - buttonWidth / 2 + xOffset);
            setTranslateY(getAppHeight() / 3.0 + yOffset);
            setOnMouseClicked(e -> Player.setDifficulty(name));
        }
    }

    private static class defaultButton extends StackPane {
        public defaultButton(String name, Runnable action) {
            var bg = new Rectangle(defaultButtonWidth, defaultButtonHeight);
            bg.setStroke(Paint.valueOf("#850300"));

            var text = getUIFactoryService().newText(name, (Color) Paint.valueOf("#850300"), 18);

            bg.fillProperty().bind(
                    Bindings.when(hoverProperty()).then((Color) Paint.valueOf("#850300")).otherwise((Color) Paint.valueOf("#121212"))
            );

            text.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Paint.valueOf("#121212")).otherwise((Color) Paint.valueOf("#850300"))
            );

            setOnMouseClicked(e -> action.run());


            getChildren().addAll(bg, text);
        }
    }
}