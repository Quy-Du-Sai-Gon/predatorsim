package org.quydusaigon.predatorsim;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.quydusaigon.predatorsim.util.Prefabs;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Group root;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Collision detection demo");
        stage.setWidth(1000);
        stage.setHeight(800);

        root = Prefabs.ROOT();
        var scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}