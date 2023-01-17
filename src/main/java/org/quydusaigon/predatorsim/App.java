package org.quydusaigon.predatorsim;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.quydusaigon.predatorsim.gameengine.GameLoop;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.Prefabs;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Group root;
    public static final int windowHeight = 800;
    public static final int windowWidth = 1000;
    private static GameLoop loop;

    private static float timeStep = 0.5f;

    private static AnchorPane left;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Predatorsim");

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("UI.fxml"));
        Parent parent = fxmlLoader.load();
        left = (AnchorPane)fxmlLoader.getNamespace().get("leftAnchorPane");
        var scene = new Scene(parent);
        stage.setScene(scene);

        load(Level::main);
        loop = new GameLoop();

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