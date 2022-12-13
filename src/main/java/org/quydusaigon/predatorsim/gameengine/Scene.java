package org.quydusaigon.predatorsim.gameengine;

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
        // Update
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
        // Render

        // Collision
    }

    public void addGameObject(GameObject newGameObject) {
        gameObjects.add(newGameObject);
    }

}
