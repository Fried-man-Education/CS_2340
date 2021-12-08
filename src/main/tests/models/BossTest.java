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
        assert(boss instanceof Enemy);
    }

    @Test
    public void testGetBoss() {
        Boss boss = Boss.getBoss();
        assert(boss instanceof Boss);
    }

    @Test
    public void parentNotChild() {
        Enemy enemy = new Enemy("name", "color", 5, 5);
        assert(!(enemy instanceof Boss));
    }

    @Test
    public void bossMoreHP() {
        Enemy boss = Boss.getBoss();
        List<Enemy> enemies = Enemies.getEnemiesData();
        for (Enemy e : enemies) {
            assert(e.getHp() < boss.getHp());
        }
    }

    @Test
    public void bossDifferentColor() {
        Enemy boss = Boss.getBoss();
        List<Enemy> enemies = Enemies.getEnemiesData();
        for (Enemy e : enemies) {
            assert(!e.getColor().equals(boss.getColor()));
        }
    }

    @Test
    public void bossSlower() {
        Enemy boss = Boss.getBoss();
        List<Enemy> enemies = Enemies.getEnemiesData();
        for (Enemy e : enemies) {
            assert(e.getSpeed() > boss.getSpeed());
        }
    }
}