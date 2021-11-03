package models;
import org.junit.Test;
import static org.junit.Assert.*;
import com.theswagbois.towerdefense.models.Combat;

public class CombatTest {
    //#9 Alik Emelianov
    @Test
    public void testCombatStarted() {
        Combat.setCombatStarted(true);
        assertTrue(Combat.isCombatStarted());
    }

    //#10 Alik Emelianov
    @Test
    public void testCombatEnded() {
        Combat.setCombatStarted(false);
        assertFalse(Combat.isCombatStarted());
    }
}
