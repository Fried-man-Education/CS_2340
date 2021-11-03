package ui;

import com.theswagbois.towerdefense.ui.GamePanel;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GamePanelTest {
    private static final int TIMEOUT = 200;
    private GamePanel gamePanel;

    @Before
    public void setup() {
        gamePanel = new GamePanel();
    }

    //#19 Joseph Vitko
    @Test(timeout = TIMEOUT)
    public void testInit() {
        assertTrue(gamePanel.getChildren().stream().count() > 0);
        assertTrue(gamePanel.isInitialized());
    }

    //#20 Andrew Friedman
    @Test(timeout = TIMEOUT)
    public void testSetSelectedColor() {
        Color temp = gamePanel.getSelectedColor();
        gamePanel.setSelectedColor(Color.WHITE);
        assertEquals(Color.WHITE, gamePanel.getSelectedColor());
        if (temp != Color.WHITE) {
            assertNotEquals(temp, gamePanel.getSelectedColor());
        }
    }

    //#21 Andrew Friedman
    @Test(timeout = TIMEOUT)
    public void testGetSelectedColor() {
        Color temp = gamePanel.getSelectedColor();
        gamePanel.setSelectedColor(Color.WHITE);
        assertEquals(Color.WHITE, gamePanel.getSelectedColor());
        if (temp != Color.WHITE) {
            assertNotEquals(temp, gamePanel.getSelectedColor());
        }
    }

    //#22 Andrew Friedman
    @Test(timeout = TIMEOUT)
    public void testSetSelectedIndex() {
        int temp = gamePanel.getSelectedIndex();
        gamePanel.setSelectedIndex(1);
        assertEquals(1, gamePanel.getSelectedIndex());
        if (temp != 1) {
            assertNotEquals(temp, gamePanel.getSelectedIndex());
        }
    }

    //#23 Kobe Beard
    @Test(timeout = TIMEOUT)
    public void testGetSelectedIndex() {
        int temp = gamePanel.getSelectedIndex();
        gamePanel.setSelectedIndex(1);
        assertEquals(1, gamePanel.getSelectedIndex());
        if (temp != 1) {
            assertNotEquals(temp, gamePanel.getSelectedIndex());
        }
    }

    //#24 Kobe Beard
    @Test(timeout = TIMEOUT)
    public void testIsInitialized() {
        assertTrue(gamePanel.isInitialized());
        gamePanel.setInitialized(false);
        assertTrue(!gamePanel.isInitialized());
    }

    //#25 Kobe Beard
    @Test(timeout = TIMEOUT)
    public void testSetInitialized() {
        gamePanel.setInitialized(false);
        assertTrue(!gamePanel.isInitialized());
        gamePanel.setInitialized(true);
        assertTrue(gamePanel.isInitialized());
    }
}
