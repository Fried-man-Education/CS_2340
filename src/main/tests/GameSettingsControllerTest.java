//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import static org.junit.Assert.assertFalse;

public class GameSettingsControllerTest {
    /*private static final int TIMEOUT = 200;
    private GameSettingsController settingsScreen;

    @Before
    public void setup() {
        settingsScreen = new GameSettingsController();
    }

    // Andrew Friedman's Test
    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertNull(settingsScreen.getTempDifficulty());
    }

    // Andrew Friedman's Test
    @Test(timeout = TIMEOUT)
    public void testCheckValidName() {
        assertFalse(settingsScreen.checkValidName(null));
        assertFalse(settingsScreen.checkValidName(""));
        assertFalse(settingsScreen.checkValidName("     ")); // whitespace
        assertTrue(settingsScreen.checkValidName("Andrew Friedman"));
        assertTrue(settingsScreen.checkValidName("1234567890"));
        assertTrue(settingsScreen.checkValidName("!@#$%^&*()"));
    }

    // Nicholas Hulston's Test
    @Test(timeout = TIMEOUT)
    public void testNames() {
        boolean validName = GameSettingsController.checkValidName("");
        assertFalse(validName);

        validName = GameSettingsController.checkValidName(" ");
        assertFalse(validName);

        validName = GameSettingsController.checkValidName("a");
        assertTrue(validName);

        validName = GameSettingsController.checkValidName("Hello!   ");
        assertTrue(validName);

        validName = GameSettingsController.checkValidName("   Test    ");
        assertTrue(validName);
    }

    // Andrew Friedman's Test
    @Test(timeout = TIMEOUT)
    public void testNameTrim() {
        GameController.setName("  a   ");
        assertEquals(GameController.getName(), "a");

        GameController.setName("    b");
        assertEquals(GameController.getName(), "b");

        GameController.setName("c      ");
        assertEquals(GameController.getName(), "c");

        GameController.setName("     Hello, my name is Andrew!      !      ");
        assertEquals(GameController.getName(), "Hello, my name is Andrew!      !");
    }*/
}
