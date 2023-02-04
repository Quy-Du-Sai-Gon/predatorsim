package org.quydusaigon.predatorsim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.gameengine.GameLoop;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Group root;
    private static GameLoop loop;

    private static Pane simulationWindow;

    public static Stage getStage() {
        return stage;
    }

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

        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/sus.png"))));
        stage.show();
    }

    private Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("UI.fxml"));
        Parent fxml = fxmlLoader.load();

        simulationWindow = (Pane) fxmlLoader.getNamespace().get("simulationWindow");

        return fxml;
    }

    public static void setStageSize(double width, double height) {
        stage.setWidth(width);
        stage.setHeight(height);
    }

    private static void setRoot(Group root) {
        var children = simulationWindow.getChildren();
        children.remove(App.root);
        children.add(root);
        App.root = root;
        Output.getInstance().resetData();
    }

    /**
     * Reset the level to a blank scene.
     */
    public static void clearLevel() {
        var newRoot = GameObject.create(TransformInit.DEFAULT, null);
        setRoot(newRoot);
    }

    /**
     * Run the level initializer code.
     * 
     * @param levelInit level initializer code.
     */
    public static void initLevel(Runnable levelInit) {
        levelInit.run();
        GameLoop.reset();
    }

    /**
     * Clear and run the new level initializer code.
     * 
     * @param levelInit level initializer code.
     * @see #clearLevel()
     * @see #initLevel(Runnable)
     */
    public static void load(Runnable levelInit) {
        clearLevel();
        initLevel(levelInit);
    }

    public static GameLoop getLoop() {
        return loop;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        try{
            UI.scheduledExecutorService.shutdownNow();
        } catch (Exception e){
            System.out.println("App Exited without any operations");
        }
        super.stop();
    }
}