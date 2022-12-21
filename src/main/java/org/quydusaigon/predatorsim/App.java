package org.quydusaigon.predatorsim;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Group root;

    @Override
    public void start(Stage stage) throws IOException {
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}