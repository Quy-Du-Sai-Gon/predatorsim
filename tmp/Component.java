package org.quydusaigon.predatorsim.gameengine;

import javafx.geometry.Point2D;
import org.quydusaigon.predatorsim.gameengine.components.Transform;

import java.util.Optional;

public abstract class Component implements Cloneable {
    private GameObject gameObject;

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

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    public static GameObject instantiate(GameObject prefab, Point2D position) throws CloneNotSupportedException {
        GameObject temp = prefab.clone();

        if(temp.getComponent(Transform.class).isPresent()){
            temp.getComponent(Transform.class).orElseThrow().setPosition(position);
        }
        else{
            temp.addComponent(new Transform(0,0)).setPosition(position);

        }

        return temp;
    }
}
