package com.tower_defense.tower_defense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class TitleScreenController extends MainApplication {
    @FXML
    public void onStartButtonClick(ActionEvent actionEvent) throws IOException { // TODO: Find Stage with less references
        loadStage("GameSettingsScreen.fxml", (Stage)((Node) actionEvent.getSource()).getScene().getWindow());
    }
}
