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
    public static int windowHeight = 800;
    public static int windowWidth = 1000;

    @Override
    public void start(Stage stage) {
        stage.setTitle("hehe");
        stage.setHeight(windowHeight);
        stage.setWidth(windowWidth);

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