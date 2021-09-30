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

    /* JUNIT TEST IDEAS:
       - checking initialization (global variable values)
       - checking difficultMoney() values
       - checking difficultHealth() values
       - If you can't think of anything change the public variables "name" &
       "difficulty" to private. Then, make getter & setter functions and test those.
     */
}
