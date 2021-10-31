import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MainApplication extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {

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
        getGameWorld().addEntityFactory(new TowerFactory());

        run(() -> {
            spawn("Ally", FXGLMath.randomPoint(
                    new Rectangle2D(0, 0, getAppWidth(), getAppHeight()))
            );

            spawn("Enemy", FXGLMath.randomPoint(
                    new Rectangle2D(0, 0, getAppWidth(), getAppHeight()))
            );
        }, Duration.seconds(1));

        super.initGame();
    }
}
