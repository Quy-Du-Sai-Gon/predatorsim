module org.quydusaigon.predatorsim {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.quydusaigon.predatorsim to javafx.fxml;
    exports org.quydusaigon.predatorsim;
}
