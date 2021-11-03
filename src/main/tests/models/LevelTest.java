package models;
import com.theswagbois.towerdefense.models.Level;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class LevelTest {
    private Level level;
    @Before
    public void setup() {
        Point2D randPoint = new Point2D(1, 1);
        ArrayList<Point2D> randWayPoints = new ArrayList<>();
        int index = 1;
        int numEnemies = 10;
        level = new Level(randPoint, randWayPoints, index, numEnemies);
    }

    //#11 Alik Emelianov
    @Test
    public void testGetSpawnPoint() {
        Point2D randSpawnPoint = new Point2D(1, 1);
        Point2D returnedSpawnPoint = level.getSpawnPoint();
        assertEquals(randSpawnPoint, returnedSpawnPoint);
    }

    //#12 Alik Emelianov
    @Test
    public void testSetSpawnPoint() {
        Point2D newSpawnPoint = new Point2D(10, 20);
        level.setSpawnPoint(newSpawnPoint);
        assertEquals(newSpawnPoint, level.getSpawnPoint());
    }

    //#13 Alik Emelianov
    @Test
    public void testGetWayPoints() {
        ArrayList<Point2D> sameWayPoints = new ArrayList<>();
        assertEquals(sameWayPoints, level.getWayPoints());
    }

    //#14 Andrew Friedman
    @Test
    public void testSetWayPoints() {
        ArrayList<Point2D> newWayPoints = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Point2D wayPoint = new Point2D(i, i);
            newWayPoints.add(wayPoint);
        }
        level.setWayPoints(newWayPoints);
        assertEquals(newWayPoints, level.getWayPoints());
    }

    //#15 Andrew Friedman
    @Test
    public void testSetActiveLevel() {
        Level.setActiveLevel(level);
        assertEquals(level, Level.getActiveLevel());
    }

    //#16 Andrew Friedman
    @Test
    public void testGetIndex() {
        assertEquals(1, level.getIndex());
    }
}
