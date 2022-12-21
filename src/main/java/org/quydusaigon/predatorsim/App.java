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
    private final int numberOfAnimals = 100;

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize our stage
        stage = new Stage();
        stage.setTitle("Testing");
        stage.setWidth(this.windowWidth);
        stage.setHeight(this.windowHeight);
        stage.setFullScreen(true);

        // Initialize our gameengine.Scene
        // org.quydusaigon.predatorsim.gameengine.Scene scene1 = new
        // org.quydusaigon.predatorsim.gameengine.Scene();

        for (int i = 0; i < this.numberOfAnimals; i++) {

            // int posX = (int) (Math.random() * (this.windowWidth - 0)) + 0;
            // int posY = (int) (Math.random() * (this.windowHeight - 0)) + 0;
            // Initialize our Transform components
            // Transform transform = new Transform(posX, posY);

            // Initialize our GameObjects with the given Transform components objects
            // GameObject obj = new GameObject(transform);

            // Initialize images for GameObjects
            // Image image = new Image(
            // "D:\\My CSE\\Programming\\Java
            // OOP\\predatorsim\\src\\main\\resources\\org\\quydusaigon\\predatorsim\\images\\animal\\prey.jpg");

            // Initialize our Sprite components with the Images objects above
            // Sprite sprite = new Sprite(image);

            // Add the corresponding Sprite component objects into the GameObject objects
            // obj.addComponent(sprite);

            // Add the GameObject objects into the gameengine.Scene object
            // scene1.addGameObject(obj);
        }
        // Execute the start function of gameengine.Scene object
        // scene1.start();

        // Begin our AnimationTimer for looping
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // For each iteration of AnimationTimer, update function of gameengine.Scene is
                // executed
                // scene1.update();
            }
        };
        timer.start();

        Pane pane = new Pane();
        // List<GameObject> objectList = scene1.getListGameObject();
        // for (GameObject gameobject : objectList) {
        // pane.getChildren().addAll(gameobject.getComponent(Sprite.class).get().getRenderedBox());
        // }

        Scene scene = new Scene(pane, Color.BEIGE);
        stage.setScene(scene);
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