package org.quydusaigon.predatorsim;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.quydusaigon.predatorsim.gameengine.GameObject;
import org.quydusaigon.predatorsim.gameengine.Scene;
import org.quydusaigon.predatorsim.gameengine.components.Sprite;
import org.quydusaigon.predatorsim.gameengine.components.Transform;


public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {

        Scene scene1 = new Scene();

        Transform transform1 = new Transform(100, 100);
        Transform transform2 = new Transform(200, 200);

        GameObject obj1 = new GameObject(transform1);
        GameObject obj2 = new GameObject(transform2);

        Image image1 = new Image("D:\\My CSE\\Programming\\Java OOP\\predatorsim\\src\\main\\resources\\org\\quydusaigon\\predatorsim\\images\\animal\\prey.jpg");
        Image image2 = new Image("D:\\My CSE\\Programming\\Java OOP\\predatorsim\\src\\main\\resources\\org\\quydusaigon\\predatorsim\\images\\animal\\predator.jpg");

        Sprite sprite1 = new Sprite(image1);
        Sprite sprite2 = new Sprite(image2);

        obj1.addComponent(sprite1);

    }

    public static void main(String[] args) {
        launch();
    }
}