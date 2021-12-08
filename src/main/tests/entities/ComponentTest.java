package entities;

import com.theswagbois.towerdefense.entities.components.EnemyComponent;
import com.theswagbois.towerdefense.entities.components.TowerComponent;
import com.theswagbois.towerdefense.models.Boss;
import com.theswagbois.towerdefense.models.Enemy;
import com.theswagbois.towerdefense.models.Tower;
import com.theswagbois.towerdefense.services.Towers;
import org.junit.Test;

public class ComponentTest {
    @Test
    public void enemyComponentHasCorrectEnemy() {
        Enemy boss = Boss.getBoss();
        EnemyComponent ec = new EnemyComponent(boss, null);
        assert (ec.getEnemy() == boss);
    }

    @Test
    public void enemyComponentHasCorrectHp() {
        Enemy boss = Boss.getBoss();
        int hp = boss.getHp();
        EnemyComponent ec = new EnemyComponent(boss, null);
        assert (ec.getHp() == hp);
    }
}
