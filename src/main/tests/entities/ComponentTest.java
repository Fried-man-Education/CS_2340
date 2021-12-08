package entities;

import com.theswagbois.towerdefense.entities.components.EnemyComponent;
import com.theswagbois.towerdefense.models.Boss;
import com.theswagbois.towerdefense.models.Enemy;
import com.theswagbois.towerdefense.services.Enemies;
import org.junit.Test;

import java.util.List;

public class ComponentTest {
    @Test
    public void enemyComponentHasCorrectEnemy() {
        Enemy boss = Boss.getBoss();
        EnemyComponent ec = new EnemyComponent(boss, null);
        assert(ec.getEnemy() == boss);
    }
}
