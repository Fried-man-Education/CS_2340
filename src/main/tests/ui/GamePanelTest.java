package ui;

import com.theswagbois.towerdefense.models.Level;
import com.theswagbois.towerdefense.services.Levels;
import com.theswagbois.towerdefense.services.Towers;
import com.theswagbois.towerdefense.ui.GamePanel;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GamePanelTest {
    private static final int TIMEOUT = 200;
    private GamePanel gamePanel;

    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

    @Before
    public void setup() {
        Towers.setNumTowers(0);
        Level l = new Level(new Point2D(0, 0), new ArrayList<Point2D>(), 0, 0, 1);
        Level.setActiveLevel(l);
        Levels.getLevels().add(l);
        Levels.setLevelCount(1);
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
