import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TowerFactory implements EntityFactory {
    @Spawns("Ally")
    public Entity newAlly(SpawnData data) {
        Texture texture = FXGL.texture("brick.png").multiplyColor(Color.GREEN);

        return FXGL.entityBuilder(data)
                .view(texture)
                .with(new ProjectileComponent(new Point2D(-1, 0), 50))
                .build();
    }
}
