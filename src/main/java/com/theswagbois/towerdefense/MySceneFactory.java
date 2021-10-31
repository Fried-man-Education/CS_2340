package com.theswagbois.towerdefense;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

public class MySceneFactory extends SceneFactory {
    @Override
    public FXGLMenu newMainMenu() {
        return new WelcomeMenu();
    }

    @Override
    public FXGLMenu newGameMenu() {
        return new SettingsMenu();
    }


}
