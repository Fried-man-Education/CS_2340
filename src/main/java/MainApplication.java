import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.math.FXGLMath;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.awt.*;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MainApplication extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("Tower Defense");
        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setIntroEnabled(false);
        gameSettings.setCloseConfirmation(false);
        gameSettings.setManualResizeEnabled(false);
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new WelcomeMenu();
            }
        });
    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, () -> {
            System.out.println("ouchie");
            //getNotificationService().pushNotification("test");
        });

        super.initInput();
    }

    @Override
    protected void initGame() {
        getGameScene().setCursor(Cursor.DEFAULT);

        getGameWorld().addEntityFactory(new TowerFactory());
        getGameWorld().addEntityFactory(new EnemyFactory());

//        run(() -> {
//            spawn("Enemy", FXGLMath.randomPoint(
//                    new Rectangle2D(0, 0, getAppWidth(), getAppHeight()))
//            );
//        }, Duration.seconds(1));

        super.initGame();
    }
}
