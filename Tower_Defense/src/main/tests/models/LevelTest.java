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
        double enemySpawnDelay = 1;
        level = new Level(randPoint, randWayPoints, index, numEnemies, enemySpawnDelay);
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

    //#14 Nick Hulston
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

    //#15 Nick Hulston
    @Test
    public void testSetActiveLevel() {
        Level.setActiveLevel(level);
        assertEquals(level, Level.getActiveLevel());
    }

    //#16 Nick Hulston
    @Test
    public void testGetIndex() {
        int index = 1;
        assertEquals(index, level.getIndex());
    }

    //#17 Joseph Vitko
    @Test
    public void testGetNumEnemies() {
        int numEnemies = 10;
        assertEquals(numEnemies, level.getNumEnemies());
    }

    //#18 Joseph Vitko
    @Test
    public void testToString() {
        String correct = "Level{spawnPoint=" + level.getSpawnPoint();
        correct += ", waypoints=" + level.getWayPoints();
        correct += ", index=" + level.getIndex();
        correct += ", numEnemies=" + level.getNumEnemies() + "}";
        assertEquals(correct, level.toString());
    }
}
