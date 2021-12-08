package models;

import com.theswagbois.towerdefense.models.Boss;
import com.theswagbois.towerdefense.models.Enemy;
import com.theswagbois.towerdefense.services.Enemies;
import org.junit.Test;

import java.util.List;

public class BossTest {
    @Test
    public void testBossIsEnemy() {
        Boss boss = Boss.getBoss();
        assert (boss instanceof Enemy);
    }

    @Test
    public void testGetBoss() {
        Enemy boss = Boss.getBoss();
        assert (boss instanceof Boss);

        assert (boss.getSpeed() != 0);
        assert (!boss.getName().equals(""));
        assert (boss.getHp() > 0);
    }

    @Test
    public void bossMoreHP() {
        Enemy boss = Boss.getBoss();
        List<Enemy> enemies = Enemies.getEnemiesData();
        for (Enemy e : enemies) {
            assert (e.getHp() < boss.getHp());
        }
    }

    @Test
    public void bossDifferentColor() {
        Enemy boss = Boss.getBoss();
        List<Enemy> enemies = Enemies.getEnemiesData();
        for (Enemy e : enemies) {
            assert (!e.getColor().equals(boss.getColor()));
        }
    }

    @Test
    public void bossSlower() {
        Enemy boss = Boss.getBoss();
        List<Enemy> enemies = Enemies.getEnemiesData();
        for (Enemy e : enemies) {
            assert (e.getSpeed() > boss.getSpeed());
        }
    }
}