package com.theswagbois.towerdefense;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;

public class SettingsMenu extends FXGLMenu {

    public SettingsMenu() {
        super(MenuType.MAIN_MENU);
        // TODO set cursor to default

        var button = new StartButton("Start new game", this::fireNewGame);
        button.setTranslateX(getAppWidth() / 2.0 - 100);
        button.setTranslateY(getAppHeight() * 2.0 / 3.0 - 20);

        var text = new Text("Configuration");
        text.setWrappingWidth(500);
        text.setTranslateY(getAppHeight() / 3.0);
        text.setTranslateX(getAppWidth() / 2.0 - 200);
        text.setFont(Font.font(30));

        getContentRoot().getChildren().add(button);
        getContentRoot().getChildren().add(text);
    }

    private static class StartButton extends StackPane {
        public StartButton(String name, Runnable action) {

            var bg = new Rectangle(200, 40);
            bg.setStroke(Color.WHITE);

            var text = getUIFactoryService().newText(name, Color.WHITE, 18);

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