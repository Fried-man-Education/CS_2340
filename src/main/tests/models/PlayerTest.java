package models;

import com.theswagbois.towerdefense.models.Player;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class PlayerTest {
    @Test
    public void testCheckValidName() {
        assertFalse(Player.validName(null));
        assertFalse(Player.validName(""));
        assertFalse(Player.validName("     "));
        assertTrue(Player.validName("  a   "));
        assertTrue(Player.validName("Andrew Friedman"));
        assertTrue(Player.validName("1234567890"));
        assertTrue(Player.validName("!@#$%^&*()"));
    }

    @Test
    public void testNameTrim() {
        Player.setName("  a   ");
        assertEquals(Player.getName(), "a");

        Player.setName("    b");
        assertEquals(Player.getName(), "b");

        Player.setName("c      ");
        assertEquals(Player.getName(), "c");

        Player.setName("     Hello, my name is Andrew!      !      ");
        assertEquals(Player.getName(), "Hello, my name is Andrew!      !");
    }

    @Test
    public void testDifficulty() {
        boolean thrown = false;
        try {
            Player.setDifficulty(null);
        } catch (Exception ignored) {
            thrown = true;
        }

        assertTrue(thrown);

        thrown = false;
        try {
            Player.setDifficulty("Easy");
        } catch (Exception ignored) {
            thrown = true;
        }

        assertFalse(thrown);
    }

    @Test
    public void testMoneyAndHpEasy() {
        Player.setDifficulty("Easy");
        // CalculateMoneyAndHp is implicitly called ^

        assertEquals(1000, Player.getMoney());
        assertEquals(200, Player.getHp());
    }

    @Test
    public void testMoneyAndHpMedium() {
        Player.setDifficulty("Medium");
        // CalculateMoneyAndHp is implicitly called ^

        assertEquals(500, Player.getMoney());
        assertEquals(100, Player.getHp());
    }

    @Test
    public void testMoneyAndHpHard() {
        Player.setDifficulty("Hard");
        // CalculateMoneyAndHp is implicitly called ^

        assertEquals(250, Player.getMoney());
        assertEquals(50, Player.getHp());
    }

    @Test
    public void testDecreaseMoney() {
        Player.setDifficulty("Easy");
        // Money should be 1000

        Player.decreaseMoney(500);
        assertEquals(500, Player.getMoney());
        assertEquals(500, Player.getLastExpense());
    }

    @Test
    public void testDecreaseHP() {
        Player.setDifficulty("Medium");
        // HP should be 100

        Player.decreaseHP(50);
        assertEquals(50, Player.getHp());
    }
}
