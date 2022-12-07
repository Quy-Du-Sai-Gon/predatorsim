package org.quydusaigon.predatorsim;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.paint.ImagePattern;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {




        Rectangle rect = new Rectangle(30, 30);
        Ellipse elli = new Ellipse(30, 30);

        elli.setCenterX(300);
        elli.setCenterY(300);
        rect.setX(500);
        rect.setY(500);

        primaryStage.setTitle("Testing");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);
        Image icon = new Image("D:\\demo\\src\\icon.jpg");
        primaryStage.getIcons().add(icon);


        int speed = 5;
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                double vectorX = (elli.getCenterX() - rect.getX())/100.0;
                double vectorY = (elli.getCenterY() - rect.getY())/100.0;
                Point2D p = new Point2D(vectorX, vectorY);
                p = p.normalize().multiply(speed);

                double recNewX = rect.getX() + p.getX();
                double recNewY = rect.getY() + p.getY();
                if (recNewX < 0) {
                    recNewX = 0;
                }
                if (recNewY < 0) {
                    recNewY = 0;
                }
                rect.setX(recNewX);
                rect.setY(recNewY);

                double elliNewX = elli.getCenterX() + p.getX();
                double elliNewY = elli.getCenterY() + p.getY();
                if (elliNewX < 0) {
                    elliNewX = 0;
                }
                if (elliNewY < 0) {
                    elliNewY = 0;
                }
                elli.setCenterX(elliNewX);
                elli.setCenterY(elliNewY);

            }
        };
        timer.start();

        Pane root = new Pane(rect, elli);
        Scene scene = new Scene(root, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}