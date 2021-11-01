module com.tower_defense.tower_defense {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.almasb.fxgl.all;
    requires annotations;
    requires junit;


    opens com.tower_defense.tower_defense to javafx.fxml;
    exports com.tower_defense.tower_defense;
}