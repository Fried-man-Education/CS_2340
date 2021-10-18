package com.tower_defense.tower_defense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSettingsController extends MainApplication {

    @FXML
    private TextField nameField;
    private String tempDifficulty;

    @FXML
    public void onNextButtonClick(ActionEvent actionEvent) throws IOException {
        // Find Stage with less references
        if (checkValidName(nameField.getText()) && tempDifficulty != null) {
            GameController.setName(nameField.getText());
            GameController.setDifficulty(tempDifficulty);
            loadStage("GameScreen.fxml",
                    (Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        }
    }

    @FXML
    public void onDifficultyChange(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();
        tempDifficulty = clickedButton.getText();
    }

    public static boolean checkValidName(String name) {
        return name != null && name.trim().length() > 0;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public String getTempDifficulty() {
        return tempDifficulty;
    }

    public void setTempDifficulty(String tempDifficulty) {
        this.tempDifficulty = tempDifficulty;
    }
}
