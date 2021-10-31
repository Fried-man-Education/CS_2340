package com.theswagbois.towerdefense;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.beans.binding.Bindings;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;

public class WelcomeMenu extends FXGLMenu {

    private Pane mainPane = new Pane();

    public WelcomeMenu() {
        super(MenuType.MAIN_MENU);
        // TODO set cursor to default

        getContentRoot().getChildren().add(mainPane);
        setFirstMenu();
    }

    private void setFirstMenu() {
        var button = new StartButton("Start new game", this::setSecondMenu);
        button.setTranslateX(getAppWidth() / 2.0 - 100);
        button.setTranslateY(getAppHeight() * 2.0 / 3.0 - 20);

        var text = new Text("Welcome to Tower Defense");
        text.setWrappingWidth(500);
        text.setTranslateY(getAppHeight() / 3.0);
        text.setTranslateX(getAppWidth() / 2.0 - 200);
        text.setFont(Font.font(30));

        mainPane.getChildren().add(button);
        mainPane.getChildren().add(text);
    }

    private void setSecondMenu() {
        System.out.println("set second menu");
        getContentRoot().getChildren().remove(mainPane);
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

            setOnMouseClicked(e -> {
                action.run();
            });


            getChildren().addAll(bg, text);
        }
    }
}