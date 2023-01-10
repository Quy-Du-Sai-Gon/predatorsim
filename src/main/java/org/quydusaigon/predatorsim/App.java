package org.quydusaigon.predatorsim;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.quydusaigon.predatorsim.gameengine.GameLoop;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Group root;

    @Override
    public void start(Stage stage) {
        root = Level.main.get();
        var scene = new Scene(root);
        stage.setScene(scene);

        var loop = new GameLoop();
        loop.start();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}