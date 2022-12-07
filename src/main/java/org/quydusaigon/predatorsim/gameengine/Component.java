package org.quydusaigon.predatorsim.gameengine;

public abstract class Component {

    private GameObject gameObject;

    public void awake() {
    }

    public void start() {
    }

    public void update() {
    }

    public void onAdded() {
    }

    public void onRemoved() {
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
