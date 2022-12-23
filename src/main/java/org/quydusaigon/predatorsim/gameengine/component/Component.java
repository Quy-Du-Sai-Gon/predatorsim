package org.quydusaigon.predatorsim.gameengine.component;

import java.util.Optional;
import java.util.stream.Stream;

import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Prefab;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;

public class Component {

    private Group gameObject;

    /*
     * Instantiation
     */

    public static void instantiate(Prefab prefab, TransformInit tf, Group parent) {
        GameObject.instantiate(prefab, tf, parent);
    }

    public static void instantiate(Prefab prefab, TransformInit tf) {
        GameObject.instantiate(prefab, tf);
    }

    public static void instantiate(Prefab prefab) {
        GameObject.instantiate(prefab);
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

    /*
     * Components methods
     */

    public <T extends Component> Optional<T> getComponent(Class<T> type) {
        return GameObject.getComponent(getGameObject(), type);
    }

    public <T extends Component> Stream<T> getComponents(Class<T> type) {
        return GameObject.getComponents(getGameObject(), type);
    }

    public <T extends Component> T addComponent(T component) {
        return GameObject.addComponent(getGameObject(), component);
    }

    /*
     * Transforms
     */

    public DoubleProperty posX() {
        return GameObject.posX(getGameObject());
    }

    public DoubleProperty posY() {
        return GameObject.posY(getGameObject());
    }

    public DoubleProperty rotate() {
        return GameObject.rotate(getGameObject());
    }

    public DoubleProperty scaleX() {
        return GameObject.scaleX(getGameObject());
    }

    public DoubleProperty scaleY() {
        return GameObject.scaleY(getGameObject());
    }

}
