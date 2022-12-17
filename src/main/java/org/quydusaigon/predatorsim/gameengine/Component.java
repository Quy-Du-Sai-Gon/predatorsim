package org.quydusaigon.predatorsim.gameengine;

import javafx.scene.Group;
import org.quydusaigon.predatorsim.gameengine.components.TransformInit;

public class Component {

    //
    private Group gameObject;

    public static void instantiate(Prefab prefab, TransformInit tf, Group parent) {
        /*TO DO*/
    }

    public static void instantiate(Prefab prefab, TransformInit tf){
        /*TO DO*/
    }

    public static void instantiate(Prefab prefab){
        /*TO DO*/
    }

    public static void destroy(Group group){
        /*TO DO*/
    }

    public static void destroy(Component component){
        /*TO DO*/
    }


    protected void setGameObject(Group group){
        /*TO DO*/
    }

    public Group getGameObject() {
        return gameObject;
    }

    /*private GameObject gameObject;

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
    }*/
}
