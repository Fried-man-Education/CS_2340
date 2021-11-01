package com.theswagbois.towerdefense;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.theswagbois.towerdefense.ui.WelcomeMenu;
import org.jetbrains.annotations.NotNull;

public class MySceneFactory extends SceneFactory {
    @NotNull
    @Override
    public FXGLMenu newMainMenu() {
        return new WelcomeMenu();
    }
}
