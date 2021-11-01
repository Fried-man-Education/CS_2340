package com.theswagbois.towerdefense;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;

public class WelcomeMenu extends FXGLMenu {

    private final Pane startPane = new Pane();
    private final Pane settingsPane = new Pane();
    private static final double startButtonWidth = 200;
    private static final double startButtonHeight = 40;

    public WelcomeMenu() {
        super(MenuType.MAIN_MENU);
        // TODO set cursor to default

        getContentRoot().getChildren().add(startPane);
        setFirstMenu();
    }

    private void setFirstMenu() {
        var button = new StartButton("Start new game", this::setSecondMenu);
        button.setTranslateX(getAppWidth() / 2.0 - startButtonWidth / 2.0);
        button.setTranslateY(getAppHeight() * 2.0 / 3.0 - startButtonHeight / 2.0);

        var text = new Text("Welcome to Tower Defense");
        text.setFont(Font.font(30));
        text.setTranslateY(getAppHeight() / 3.0);
        text.setTranslateX(getAppWidth() / 2.0 - text.getLayoutBounds().getWidth() / 2);

        startPane.getChildren().add(button);
        startPane.getChildren().add(text);
    }

    private void setSecondMenu() {
        final double textFieldWidth = 200;

        var title = new Text("Configuration");
        title.setFont(Font.font(30));
        title.setTranslateX(getAppWidth() / 2.0 - title.getLayoutBounds().getWidth() / 2);
        title.setTranslateY(getAppHeight() / 3.0);

        var nameText = new Text("Enter your name");
        nameText.setFont(Font.font(15));
        nameText.setTranslateX(getAppWidth() / 2.0 - nameText.getLayoutBounds().getWidth() / 2);
        nameText.setTranslateY(getAppHeight() / 3.0 + 50);

        var nameTextField = new TextField();
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

        var beginGameButton = new StartButton("Start new game", this::fireNewGame);
        beginGameButton.setTranslateX(getAppWidth() / 2.0 - startButtonWidth / 2.0);
        beginGameButton.setTranslateY(getAppHeight() / 3.0 + 250);

        settingsPane.getChildren().addAll(title, nameText, nameTextField, difficultyText, easyButton, mediumButton, hardButton, beginGameButton);

        getContentRoot().getChildren().remove(startPane);
        getContentRoot().getChildren().add(settingsPane);
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
            setOnMouseClicked(e -> {
                Player.setDifficulty(name);
            });
        }
    }

    private static class StartButton extends StackPane {
        public StartButton(String name, Runnable action) {

            var bg = new Rectangle(startButtonWidth, startButtonHeight);
            bg.setStroke(Color.WHITE);

            var text = getUIFactoryService().newText(name, Color.WHITE, 18);

            bg.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.BLACK)
            );

            text.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.BLACK).otherwise(Color.WHITE)
            );

            setOnMouseClicked(e -> {
                action.run();
            });


            getChildren().addAll(bg, text);
        }
    }
}