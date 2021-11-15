package services;

import com.theswagbois.towerdefense.models.Level;
import com.theswagbois.towerdefense.services.Levels;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LevelsTest {
    private static final int TIMEOUT = 200;
    private Levels levels;

    @Before
    public void setup() {
        levels = new Levels();
    }

    @Test(timeout = TIMEOUT)
    public void testGetLevels() {
        assertNotEquals(new ArrayList<Level>(), levels.getLevels());
    }

    @Test(timeout = TIMEOUT)
    public void testGetLevelCount() {
        assertEquals(4, levels.getLevelCount());
        levels.setLevelCount(5);
        assertEquals(5, levels.getLevelCount());
    }

    @Test(timeout = TIMEOUT)
    public void testSetLevelCount() {
        assertEquals(1, levels.getLevelCount());
        levels.setLevelCount(4);
        assertEquals(4, levels.getLevelCount());
    }
}
