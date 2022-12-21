module org.quydusaigon.predatorsim {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens org.quydusaigon.predatorsim to javafx.fxml;

    exports org.quydusaigon.predatorsim;
    exports org.quydusaigon.predatorsim.gameengine.gameobject;
    exports org.quydusaigon.predatorsim.gameengine.component;
    exports org.quydusaigon.predatorsim.gameengine.util;
}
