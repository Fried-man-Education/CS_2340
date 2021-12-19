package services;

import com.theswagbois.towerdefense.services.Towers;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.assertEquals;

public class TowersTest {
    private static final int TIMEOUT = 200;
    private Towers towers;

    @Before
    public void setup() {
        towers = new Towers();
    }

    @Test(timeout = TIMEOUT)
    public void testGetNumTowers() {
        assertEquals(0, towers.getNumTowers());
        towers.setNumTowers(5);
        assertEquals(5, towers.getNumTowers());
    }

    @Test(timeout = TIMEOUT)
    public void testSetNumTowers() {
        assertEquals(5, towers.getNumTowers());
        towers.setNumTowers(4);
        assertEquals(4, towers.getNumTowers());
    }
}
