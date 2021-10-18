import com.tower_defense.tower_defense.GameController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


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
        assertEquals(1000, gameScreen.difficultMoney("Easy"));
        assertEquals(500, gameScreen.difficultMoney("Medium"));
        assertEquals(250, gameScreen.difficultMoney("Hard"));
    }

    //Koby Beard's Test
    @Test(timeout = TIMEOUT)
    public void testDifficultHealth() {
        assertEquals(200, gameScreen.difficultHealth("Easy"));
        assertEquals(100, gameScreen.difficultHealth("Medium"));
        assertEquals(50, gameScreen.difficultHealth("Hard"));
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

    //Alik Emelianov's Test
    @Test(timeout = 10000)
    public void testGridNumberInitialization() {
        GridPane grid = gameScreen.createGrid();
        int numGray = 0;
        int numGreen = 0;
        int numBlue = 0;
        for (Node node : grid.getChildren()) {
            Rectangle cell = (Rectangle) node;
            Color rectangleColor = (Color) cell.getFill();

            if (rectangleColor.equals(Color.GREEN)) {
                numGreen++;
            } else if (rectangleColor.equals(Color.GRAY)) {
                numGray++;
            } else if (rectangleColor.equals(Color.BLUE)) {
                numBlue++;
            }
        }
        assertEquals(32, numGray);
        assertEquals(384, numGreen);
        assertEquals(9, numBlue);
    }

    //Alik Emelianov's 2nd Test
    @Test(timeout = 10000)
    public void testGridLayoutInitialization() {
        GridPane grid = gameScreen.createGrid();
        ObservableList<Node> children = grid.getChildren();
        for (Node node : children) {
            int row = grid.getRowIndex(node);
            int col = grid.getColumnIndex(node);
            Rectangle rect = (Rectangle) node;
            if (col == 2 && row > 11) {
                assertEquals(rect.getFill(), Color.GRAY);
            } else if (row == 11 && col < 21 && col >= 2) {
                assertEquals(rect.getFill(), Color.GRAY);
            } else if (col == 21 && row >= 4 && row <= 11) {
                assertEquals(rect.getFill(), Color.GRAY);
            } else if (row >= 1 && row <= 3 && col >= 20 && col <= 22) {
                assertEquals(rect.getFill(), Color.BLUE);
            } else {
                assertEquals(rect.getFill(), Color.GREEN);
            }
        }
    }

    // Nick Hulston's Test
    @Test(timeout = TIMEOUT)
    public void testGrayTile() {
        int x = 0, y = 0;
        Rectangle temp = gameScreen.createGrayTile(x,y);
        assertEquals(x + "," + y, temp.getId());
        assertEquals(20, (int)temp.getHeight());
        assertEquals(24, (int)temp.getWidth());
        assertEquals(Color.web("0x808080ff"), temp.getFill());
    }
}
