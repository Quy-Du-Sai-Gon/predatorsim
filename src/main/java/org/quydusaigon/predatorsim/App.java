package org.quydusaigon.predatorsim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import org.quydusaigon.predatorsim.gameengine.GameLoop;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Group root;
    public static final int simulationWindowHeight = 500;
    public static final int simulationWindowWidth = 500;
    private static GameLoop loop;

    private static float timeStep = 0.5f;

    private static BorderPane left;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Predatorsim");

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("UI.fxml"));
        Parent parent = fxmlLoader.load();

        Rectangle myRect = new Rectangle(simulationWindowWidth, simulationWindowHeight,Color.TRANSPARENT);
        myRect.setX(0.0);
        myRect.setY(0.0);
        myRect.setStroke(Color.RED);

        left = (BorderPane) fxmlLoader.getNamespace().get("borderPane");
        left.setCenter(root);
        left.setTop(myRect);

        var scene = new Scene(parent);
        stage.setScene(scene);

        load(Level::main);
        loop = new GameLoop();
        stage.setResizable(true);
        stage.show();
    }

    private static void setRoot(Group root) {
        var children = left.getChildren();
        children.remove(App.root);
        children.add(root);
        App.root = root;
    }

    public static void load(Runnable levelInitializer) {
        var newRoot = GameObject.create(TransformInit.DEFAULT, null);
        setRoot(newRoot);
        levelInitializer.run();
    }

    public static GameLoop getLoop() {
        return loop;
    }

    public static float getTimeStep() {
        return timeStep;
    }

    public static void main(String[] args) {
        launch();
    }
}