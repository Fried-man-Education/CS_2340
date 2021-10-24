import com.tower_defense.tower_defense.GridController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GridControllerTest {
    private static final int TIMEOUT = 200;
    private GridController gridScreen;

    @Before
    public void setup() {
        gridScreen = new GridController();
    }
    // Nick Hulston's Test
    @Test(timeout = TIMEOUT)
    public void testGrayTile() {
        int x = 0;
        int y = 0;
        Rectangle temp = gridScreen.createGrayTile(x, y);
        assertEquals(x + "," + y, temp.getId());
        assertEquals(20, (int) temp.getHeight());
        assertEquals(24, (int) temp.getWidth());
        assertEquals(Color.web("0x808080ff"), temp.getFill());
    }

    // Joseph Vitko's Test
    @Test(timeout = TIMEOUT)
    public void testGreenTile() {
        int x = 0;
        int y = 0;
        Rectangle temp = gridScreen.createGreenTile(x, y);
        assertEquals(x + "," + y, temp.getId());
        assertEquals(20, (int) temp.getHeight());
        assertEquals(24, (int) temp.getWidth());
        assertEquals(Color.web("0x008000ff"), temp.getFill());
    }

    // Joseph Vitko's Test
    @Test(timeout = TIMEOUT)
    public void testBaseTile() {
        int x = 0;
        int y = 0;
        Rectangle temp = gridScreen.createBaseTile(x, y);
        assertEquals(x + "," + y, temp.getId());
        assertEquals(20, (int) temp.getHeight());
        assertEquals(24, (int) temp.getWidth());
        assertEquals(Color.web("0x0000ffff"), temp.getFill());
    }
    //Koby Test
    @Test(timeout = TIMEOUT)
    public void testIsBase() {
        assertTrue(gridScreen.isBase(21, 2));
        assertFalse(gridScreen.isBase(2, 100));
    }
    //Koby Test
    @Test(timeout = TIMEOUT)
    public void testIsPath() {
        assertTrue(gridScreen.isPath(2, 100));
        assertFalse(gridScreen.isPath(11, 10));
    }
}
