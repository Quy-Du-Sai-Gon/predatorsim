package org.quydusaigon.predatorsim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.quydusaigon.predatorsim.gameengine.*;
import org.quydusaigon.predatorsim.gameengine.components.Sprite;
import org.quydusaigon.predatorsim.gameengine.components.Transform;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private final int windowHeight = 600;
    private final int windowWidth = 1000;

    @Override
    public void start(Stage stage) throws IOException {
        org.quydusaigon.predatorsim.gameengine.Scene scene1 = new org.quydusaigon.predatorsim.gameengine.Scene();

        Transform transform1 = new Transform(100, 100);
        Transform transform2 = new Transform(200, 200);

        GameObject obj1 = new GameObject(transform1);
        GameObject obj2 = new GameObject(transform2);

        Image image1 = new Image("D:\\My CSE\\Programming\\Java OOP\\predatorsim\\src\\main\\resources\\org\\quydusaigon\\predatorsim\\images\\animal\\prey.jpg");
        Image image2 = new Image("D:\\My CSE\\Programming\\Java OOP\\predatorsim\\src\\main\\resources\\org\\quydusaigon\\predatorsim\\images\\animal\\predator.jpg");

        Sprite sprite1 = new Sprite(image1);
        Sprite sprite2 = new Sprite(image2);

        obj1.addComponent(sprite1);
        obj2.addComponent(sprite2);

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