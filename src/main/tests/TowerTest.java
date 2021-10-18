import com.tower_defense.tower_defense.towers.*;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// TODO: Fix error for only running this test file specifically
public class TowerTest {
    private static final int TIMEOUT = 200;

    //Andrew Friedman's Test
    @Test(timeout = TIMEOUT)
    public void testNormalTower() {
        NormalTower temp = new NormalTower(200);
        assertEquals(Color.web("0x1e90ffff"), temp.getColor());
        assertEquals(200, temp.getCost());
        assertEquals(0, temp.x);
        assertEquals(0, temp.y);
    }

    //Andrew Friedman's Test
    @Test(timeout = TIMEOUT)
    public void testSplashTower() {
        SplashTower temp = new SplashTower(300);
        assertEquals(Color.web("0xff8e21ff"), temp.getColor());
        assertEquals(300, temp.getCost());
        assertEquals(0, temp.x);
        assertEquals(0, temp.y);
    }

    //Nick Hulston's Test
    @Test(timeout = TIMEOUT)
    public void testMachineTower() {
        MachineTower temp = new MachineTower(200);
        assertEquals(Color.web("0xd300e6ff"), temp.getColor());
        assertEquals(200, temp.getCost());
        assertEquals(0, temp.x);
        assertEquals(0, temp.y);
    }


}
