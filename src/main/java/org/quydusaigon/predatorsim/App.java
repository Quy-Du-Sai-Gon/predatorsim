package org.quydusaigon.predatorsim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
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
    public static double simulationWindowHeight;
    public static double simulationWindowWidth;
    private static GameLoop loop;

    private static float timeStep = 0.5f;

    private static BorderPane simulationWindow;
    private static Stage stage;

    @Override
    
    public void start(Stage mainStage) throws IOException {
        stage = mainStage;
        stage.setTitle("Predatorsim");

        var scene = new Scene(loadFXML());
        stage.setScene(scene);

        load(Level::main);
        loop = new GameLoop();
        stage.setResizable(false);
        UI.updateSimulationWindowSize();

        stage.show();

    }

    private Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("UI.fxml"));
        Parent fxml = fxmlLoader.load();

        simulationWindow = (BorderPane) fxmlLoader.getNamespace().get("simulationWindow");

        return fxml;
    }

    public static void setStageSize(double width, double height) {
        stage.setWidth(width);
        stage.setHeight(height);
    }

    public static void setSimulationWindowSize(double width, double height) {
        simulationWindowWidth = width;
        simulationWindowHeight = height;
    }

    private static void setRoot(Group root) {
        var children = simulationWindow.getChildren();
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