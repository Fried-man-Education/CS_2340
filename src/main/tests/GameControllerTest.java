import com.tower_defense.tower_defense.GameController;
import com.tower_defense.tower_defense.GameSettingsController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
    }

    //Alik Emelianov's Test
    @Test(timeout = 10000)
    public void testGridNumberInitialization() {
        GridPane grid = gameScreen.createGrid();
        int numGray = 0, numGreen = 0, numBlue = 0;
        for(Node node: grid.getChildren()){
            Rectangle cell = (Rectangle) node;
            Color rectangleColor = (Color) cell.getFill();

            if(rectangleColor.equals(Color.GREEN)){
                numGreen++;
            } else if(rectangleColor.equals(Color.GRAY)){
                numGray++;
            } else if(rectangleColor.equals(Color.BLUE)) {
                numBlue++;
            }
        }
        assertEquals(32,numGray);
        assertEquals(384,numGreen);
        assertEquals(9,numBlue);
    }

    //Alik Emelianov's 2nd Test
    @Test(timeout = 10000)
    public void testGridLayoutInitialization() {
        int numRows = 17, numCols = 25;
        GridPane grid = gameScreen.createGrid();
        ObservableList<Node> children = grid.getChildren();
        for (Node node : children) {
            int row = grid.getRowIndex(node);
            int col = grid.getColumnIndex(node);
            Rectangle rect = (Rectangle) node;
            if(col == 2 && row > 11){
                assertEquals(rect.getFill(), Color.GRAY);
            } else if(row == 11 && col < 21 && col >= 2){
                assertEquals(rect.getFill(), Color.GRAY);
            } else if(col == 21 && row >= 4 && row <= 11){
                assertEquals(rect.getFill(), Color.GRAY);
            } else if(row >= 1 && row <= 3 && col >= 20 && col <= 22){
                assertEquals(rect.getFill(), Color.BLUE);
            } else {
                assertEquals(rect.getFill(), Color.GREEN);
            }
        }
    }

    /* JUNIT TEST IDEAS:
       - checking initialization (global variable values)
       - checking difficultMoney() values
       - checking difficultHealth() values
       - If you can't think of anything change the public variables "name" &
       "difficulty" to private. Then, make getter & setter functions and test those.
     */
}
