module com.tower_defense.tower_defense {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.tower_defense.tower_defense to javafx.fxml;
    exports com.tower_defense.tower_defense;
}