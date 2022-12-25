package org.quydusaigon.predatorsim;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Group root;

    private static Scene scene;
    private final int windowHeight = 800;
    private final int windowWidth = 1000;

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize our stage
        stage.setTitle("Testing");
        stage.setWidth(this.windowWidth);
        stage.setHeight(this.windowHeight);
        stage.setFullScreen(true);

        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    public int getWindowWidth() {
        return this.windowWidth;
    }

    public static void main(String[] args) {
        launch();
    }

}