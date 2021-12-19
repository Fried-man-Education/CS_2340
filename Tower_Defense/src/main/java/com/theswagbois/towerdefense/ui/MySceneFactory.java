package com.theswagbois.towerdefense.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import org.jetbrains.annotations.NotNull;

public class MySceneFactory extends SceneFactory {
    @NotNull
    @Override
    public FXGLMenu newMainMenu() {
        return new WelcomeMenu();
    }
}
