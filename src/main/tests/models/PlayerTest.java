package models;

import com.theswagbois.towerdefense.models.Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    //#1 Nick Hulston
    public void testCheckValidName() {
        assertFalse(Player.validName(null));
        assertFalse(Player.validName(""));
        assertFalse(Player.validName("     "));
        assertTrue(Player.validName("  a   "));
        assertTrue(Player.validName("Andrew Friedman"));
        assertTrue(Player.validName("1234567890"));
        assertTrue(Player.validName("!@#$%^&*()"));
    }

    //#2 Nick Hulston
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

    //#3 Joseph Vitko
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

    //#4 Joseph Vitko
    @Test
    public void testMoneyAndHpEasy() {
        Player.setDifficulty("Easy");
        // CalculateMoneyAndHp is implicitly called ^

        assertEquals(600, Player.getMoney());
        assertEquals(120, Player.getHp());
    }

    //#5 Andrew Friedman
    @Test
    public void testMoneyAndHpMedium() {
        Player.setDifficulty("Medium");
        // CalculateMoneyAndHp is implicitly called ^

        assertEquals(400, Player.getMoney());
        assertEquals(80, Player.getHp());
    }

    //#6 Andrew Friedman
    @Test
    public void testMoneyAndHpHard() {
        Player.setDifficulty("Hard");
        // CalculateMoneyAndHp is implicitly called ^

        assertEquals(300, Player.getMoney());
        assertEquals(60, Player.getHp());
    }

    //#7 Koby Beard
    @Test
    public void testDecreaseMoney() {
        Player.setDifficulty("Easy");
        // Money should be 1000

        Player.incrementMoney(-500);
        assertEquals(500, Player.getMoney());
        assertEquals(500, Player.getLastExpense());
    }

    //#8 Koby Beard
    @Test
    public void testDecreaseHP() {
        Player.setDifficulty("Medium");
        // HP should be 100

        Player.decreaseHP(50);
        assertEquals(50, Player.getHp());
    }
}
