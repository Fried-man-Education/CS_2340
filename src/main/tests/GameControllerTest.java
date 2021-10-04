import com.tower_defense.tower_defense.GameController;
import com.tower_defense.tower_defense.GameSettingsController;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class GameControllerTest {
    private static final int TIMEOUT = 200;
    private GameController gameScreen;

    @Before
    public void setup() {
        gameScreen = new GameController();
    }

    //Alik Emelianov's Test
    @Test(timeout = TIMEOUT)
    public void testDifficultMoney() {
        assertEquals("1000", gameScreen.difficultMoney("Easy"));
        assertEquals("500", gameScreen.difficultMoney("Medium"));
        assertEquals("250", gameScreen.difficultMoney("Hard"));
    }

    //Koby Beard's Test
    @Test(timeout = TIMEOUT)
    public void testDifficultHealth() {
        assertEquals("200", gameScreen.difficultHealth("Easy"));
        assertEquals("100", gameScreen.difficultHealth("Medium"));
        assertEquals("50", gameScreen.difficultHealth("Hard"));
    }

    // Joseph Vitko's Test
    @Test(timeout = TIMEOUT)
    public void testSetDifficulty() {
        String easy = "easy";
        GameController.setDifficulty(easy);
        assertEquals(easy, GameController.getDifficulty());

        String medium = "medium";
        GameController.setDifficulty(medium);
        assertEquals(medium, GameController.getDifficulty());

        String hard = "hard";
        GameController.setDifficulty(hard);
        assertEquals(hard, GameController.getDifficulty());
    }

    /* JUNIT TEST IDEAS:
       - checking initialization (global variable values)
       - checking difficultMoney() values
       - checking difficultHealth() values
       - If you can't think of anything change the public variables "name" &
       "difficulty" to private. Then, make getter & setter functions and test those.
     */
}
