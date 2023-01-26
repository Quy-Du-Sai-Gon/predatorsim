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
    public static double simulationWindowHeight;
    public static double simulationWindowWidth;
    private static GameLoop loop;

    private static float timeStep = 0.5f;

    private static BorderPane left;
    private static Stage stage;

    @Override
    
    public void start(Stage mainStage) throws IOException {
        stage = mainStage;

        stage.setTitle("Predatorsim");

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("UI.fxml"));
        Parent parent = fxmlLoader.load();

        left = (BorderPane) fxmlLoader.getNamespace().get("borderPane");




        left.setCenter(UI.getGridLines());
        left.setTop(root);

        var scene = new Scene(parent);
        stage.setScene(scene);

        load(Level::main);
        loop = new GameLoop();
        stage.setResizable(false);
        UI.updateSimulationWindowSize();
        stage.show();

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