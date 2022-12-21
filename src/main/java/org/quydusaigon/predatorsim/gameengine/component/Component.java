package org.quydusaigon.predatorsim.gameengine.component;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Prefab;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

import javafx.scene.Group;

public class Component {

    private Group gameObject;

    /*
     * Instantiation
     */

    public static void instantiate(Prefab prefab, TransformInit tf, Group parent) {
        prefab.instantiate(tf, parent);
    }

    public static void instantiate(Prefab prefab, TransformInit tf) {
        instantiate(prefab, tf, App.root);
    }

    public static void instantiate(Prefab prefab) {
        instantiate(prefab, TransformInit.ZERO);
    }

    /*
     * Deletion
     */

    public static void destroy(Group gameObject) {
        GameObject.destroy(gameObject);
    }

    public static void destroy(Component component) {
        GameObject.destroy(component);
    }

    public void onDestroy() {
    }

    /*
     * GameObject access
     */

    public void setGameObject(Group gameObject) {
        this.gameObject = gameObject;
    }

    public Group getGameObject() {
        return gameObject;
    }

}
