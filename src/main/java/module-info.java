module org.quydusaigon.predatorsim {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens org.quydusaigon.predatorsim to javafx.fxml;

    exports org.quydusaigon.predatorsim;
    exports org.quydusaigon.predatorsim.gameengine;

    opens org.quydusaigon.predatorsim.gameengine to javafx.fxml;
}
