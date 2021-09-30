import com.tower_defense.tower_defense.GameSettingsController;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class GameSettingsControllerTest {
    private static final int TIMEOUT = 200;
    private GameSettingsController settingsScreen;

    @Before
    public void setup() {
        settingsScreen = new GameSettingsController();
    }

    // Andrew Friedman's Test
    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertNull(settingsScreen.tempDifficulty);
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
}
