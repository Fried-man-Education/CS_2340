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

    @Test
    public void enemyComponentHasCorrectSpeed() {
        Enemy boss = Boss.getBoss();
        double speed = boss.getSpeed();
        EnemyComponent ec = new EnemyComponent(boss, null);
        assert (ec.getSpeed() == speed);
    }

    @Test
    public void upgradedTowerComponentHasGreaterDamage() {
        Tower t = new Tower("test", "", 0.5, 0.5, 0.5, 0.5, new int[]{0, 10, 10, 10});
        Towers.getTowersData().add(t);

        int originalDamage = t.getDamage();
        TowerComponent tc = new TowerComponent(1, 10);
        tc.upgrade();

        assert (tc.getDamage() > originalDamage);
    }

    @Test
    public void upgradedTowerComponentHasGreaterBulletSpped() {
        Tower t = new Tower("test", "", 0.5, 0.5, 0.5, 0.5, new int[]{0, 10, 10, 10});
        Towers.getTowersData().add(t);

        double original = t.getBulletSpeed();
        TowerComponent tc = new TowerComponent(1, 10);
        tc.upgrade();

        assert (tc.getBulletSpeed() > original);
    }

    @Test
    public void upgradedTowerComponentHasLowerAttackDelay() {
        Tower t = new Tower("test", "", 0.5, 0.5, 0.5, 0.5, new int[]{0, 10, 10, 10});
        Towers.getTowersData().add(t);

        double original = t.getAttackDelay();
        TowerComponent tc = new TowerComponent(1, 10);
        tc.upgrade();

        assert (tc.getAttackDelay() < original);
    }

    @Test
    public void upgradedTowerComponentHasGreaterAccuracy() {
        Tower t = new Tower("test", "", 0.5, 0.5, 0.5, 0.5, new int[]{0, 10, 10, 10});
        Towers.getTowersData().add(t);

        double original = t.getAccuracy();
        TowerComponent tc = new TowerComponent(1, 10);
        tc.upgrade();

        assert (tc.getAccuracy() > original);
    }

    @Test
    public void upgradedTowerComponentHasGreaterRange() {
        Tower t = new Tower("test", "", 0.5, 0.5, 0.5, 0.5, new int[]{0, 10, 10, 10});
        Towers.getTowersData().add(t);

        double original = t.getRange();
        TowerComponent tc = new TowerComponent(1, 10);
        tc.upgrade();

        assert (tc.getRange() > original);
    }
}
