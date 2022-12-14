package org.quydusaigon.predatorsim.gameengine;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import org.quydusaigon.predatorsim.gameengine.components.Sprite;
import org.quydusaigon.predatorsim.gameengine.components.Transform;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private final List<GameObject> gameObjects;

    public Scene() {
        this.gameObjects = new ArrayList<>();
    }

    public void start() {
        for (GameObject gameObject : gameObjects) {
            gameObject.start();
        }
    }

    public void update() {
        // Render
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getComponent(Sprite.class).isPresent()) {
                Point2D position = gameObject.getTransform().getPosition();
                Sprite sprite = gameObject.getComponent(Sprite.class).get();

                Rectangle renderedBox = sprite.getRenderedBox();

                renderedBox.setX(position.getX());
                renderedBox.setY(position.getY());
            }
        }

        // Update
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }


        // Collision
    }

    public void addGameObject(GameObject newGameObject) {
        gameObjects.add(newGameObject);
    }
    public List<GameObject> getListGameObject() {
        return this.gameObjects;
    }

}
