package com.theswagbois.towerdefense;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WelcomeMenu extends FXGLMenu {

    public WelcomeMenu() {
        super(MenuType.MAIN_MENU);

        var button = new StartButton("Start new game", this::fireNewGame);
        button.setTranslateX(FXGL.getAppWidth() / 2.0 - 100);
        button.setTranslateY(FXGL.getAppHeight() / 2.0 - 20);

        getContentRoot().getChildren().add(button);
    }

    private static class StartButton extends StackPane {
        public StartButton(String name, Runnable action) {

            var bg = new Rectangle(200, 40);
            bg.setStroke(Color.WHITE);

            var text = FXGL.getUIFactoryService().newText(name, Color.WHITE, 18);

            bg.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.BLACK)
            );

            text.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.BLACK).otherwise(Color.WHITE)
            );

            setOnMouseClicked(e -> action.run());

            getChildren().addAll(bg, text);
        }
    }
}