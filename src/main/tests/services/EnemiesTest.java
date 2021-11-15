package services;

import com.theswagbois.towerdefense.services.Enemies;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EnemiesTest {
    private static final int TIMEOUT = 200;
    private Enemies enemies;

    @Before
    public void setup() {
        enemies = new Enemies();
    }

    @Test(timeout = TIMEOUT)
    public void testGetNumEnemies() {
        assertEquals(4, enemies.getNumEnemies());
        enemies.setNumEnemies(5);
        assertEquals(5, enemies.getNumEnemies());
    }

    @Test(timeout = TIMEOUT)
    public void testSetNumEnemies() {
        assertEquals(3, enemies.getNumEnemies());
        enemies.setNumEnemies(4);
        assertEquals(4, enemies.getNumEnemies());
    }

    @Test(timeout = TIMEOUT)
    public void testGetEnemiesData() {
        assertEquals(new ArrayList<>(), enemies.getEnemiesData());
    }

    /*@Test(timeout = TIMEOUT)
    public void testLoadEnemiesData() {
        assertEquals(new ArrayList<>(), enemies.getEnemiesData());
        enemies.loadEnemiesData();
        assertNotEquals(new ArrayList<>(), enemies.getEnemiesData());
    }*/
}
