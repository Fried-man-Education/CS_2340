package com.tower_defense.tower_defense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSettingsController extends MainApplication {
    @FXML TextField nameField;
    public String tempDifficulty;

    @FXML
    public void onNextButtonClick(ActionEvent actionEvent) throws IOException { // TODO: Find Stage with less references
        if (checkValidName (nameField.getText()) && tempDifficulty != null) {
            GameController.name = nameField.getText();
            GameController.difficulty = tempDifficulty;
            loadStage("GameScreen.fxml", (Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        }
    }

    @FXML
    public void onDifficultyChange(ActionEvent actionEvent){
        Button clickedButton = (Button) actionEvent.getSource();
        tempDifficulty = clickedButton.getText();
    }

    public boolean checkValidName (String name) {
        return name != null && name.trim().length() > 0;
    }
}
