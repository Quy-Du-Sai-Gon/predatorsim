package org.quydusaigon.predatorsim.gameengine.component;

import java.util.Optional;
import java.util.stream.Stream;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Prefab;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;

/**
 * This is the base class for all components that compose a game object in the
 * {@code gameengine} package. The class provides serveral convenient methods
 * for operating on a component and its game object.
 * 
 * @see GameObject
 * @see <a href="https://en.wikipedia.org/wiki/Entity_component_system">Entity
 *      component system</a>
 */
public abstract class Component {

    private Group gameObject;

    /*
     * Instantiation
     */

    /**
     * Instantiates a new game object using {@code prefab}. Similar to
     * {@link GameObject#instantiate(Prefab, TransformInit, Group)}.
     * 
     * @param prefab the prefab for instantiating the new game object.
     * @param tf     the initial transform data of the game object to be created.
     * @param parent the parent of the game object to be created.
     * @see Prefab
     * @see TransformInit
     */
    public static void instantiate(Prefab prefab, TransformInit tf, Group parent) {
        GameObject.instantiate(prefab, tf, parent);
    }

    /**
     * Instantiates a new game object under {@link App#root} using {@code prefab}.
     * Similar to calling {@link GameObject#instantiate(Prefab, TransformInit)}.
     * 
     * @param prefab the prefab for instantiating the new game object.
     * @param tf     the initial transform data of the game object to be created.
     * @see Prefab
     * @see TransformInit
     */
    public static void instantiate(Prefab prefab, TransformInit tf) {
        GameObject.instantiate(prefab, tf);
    }

    /**
     * Instantiates a new game object under {@code parent} with
     * {@link TransformInit#DEFAULT} using {@code prefab}. Similar to
     * {@link GameObject#instantiate(Prefab, Group)}.
     * 
     * @param prefab the prefab for instantiating the new game object.
     * @param parent the parent of the game object to be created.
     * @see Prefab
     * @see TransformInit
     */
    public static void instantiate(Prefab prefab, Group parent) {
        GameObject.instantiate(prefab, parent);
    }

    /**
     * Instantiates a new game object under {@link App#root} with
     * {@link TransformInit#DEFAULT} using {@code prefab}. Similar to
     * {@link GameObject#instantiate(Prefab)}.
     * 
     * @param prefab the prefab for instantiating the new game object.
     * @see Prefab
     */
    public static void instantiate(Prefab prefab) {
        GameObject.instantiate(prefab);
    }

    /*
     * Deletion
     */

    /**
     * Remove {@code gameObject} and all of its descendants from their parents while
     * calling {@link #destroy(Component)} on all of their components. Similar to
     * {@link GameObject#destroy(Group)}.
     *
     * @param gameObject the game object to destroy.
     * @throws NotAGameObjectException if {@code gameObject} is not a valid game
     *                                 object created with
     *                                 {@link GameObject#create}.
     */
    public static void destroy(Group gameObject) {
        GameObject.destroy(gameObject);
    }

    /**
     * Invoke the {@code component}'s {@link Component#onDestroy() onDestroy} event
     * and remove it from its associated game object. Similar to calling
     * {@link GameObject#destroy(Component)}.
     *
     * @param component the component to be destroyed.
     */
    public static void destroy(Component component) {
        GameObject.destroy(component);
    }

    /**
     * This method is called when a component is destroyed.
     * 
     * <p>
     * Can be overridden by subclasses of {@link Component}.
     * 
     * @see #destroy(Component)
     */
    public void onDestroy() {
    }

    /*
     * GameObject access
     */

    /**
     * Sets the game object associated with this component. Should only be used
     * internally by the {@code gameengine} package.
     * 
     * @param gameObject the new game object to associate this component with.
     */
    public void setGameObject(Group gameObject) {
        this.gameObject = gameObject;
    }

    /**
     * Returns the game object associated with this component. Will be {@code null}
     * if the component has not been properly added to a game object (e.g., when
     * constructing the component, before adding the component to a game object,
     * after calling {@link #destroy(Component)} on the component, etc.).
     * 
     * @return the game object associated with this component.
     */
    public Group getGameObject() {
        return gameObject;
    }

    /*
     * Components methods
     */

    /**
     * Returns the first component of type {@code type} (if any) in the same game
     * object associated with this component. Similar to calling
     * {@code GameObject.getComponent(getGameObject(), type)}.
     * 
     * @param <T>  the type of {@link Component} to return.
     * @param type the class instance of the {@link Component} type to return.
     * @return an {@link Optional} containing the first component matching
     *         {@code type} if any.
     * @throws NotAGameObjectException if the game object associated with this
     *                                 component is {@code null} or is not a valid
     *                                 game object created with
     *                                 {@link GameObject#create}.
     * @see GameObject#getComponent(Group, Class)
     */
    public <T extends Component> Optional<T> getComponent(Class<T> type) {
        return GameObject.getComponent(getGameObject(), type);
    }

    /**
     * Returns a stream of components of type {@code type} in the same game object
     * associated with this component. Similar to calling
     * {@code GameObject.getComponents(getGameObject(), type)}.
     * 
     * @param <T>  the type of {@link Component} to return.
     * @param type the class instance of the {@link Component} type to return.
     * @return a {@link Stream} of components in the same game object associated
     *         with this component that match {@code type}.
     * @throws NotAGameObjectException if the game object associated with this
     *                                 component is {@code null} or is not a valid
     *                                 game object created with
     *                                 {@link GameObject#create}.
     * @see GameObject#getComponents(Group, Class)
     * @see Stream
     */
    public <T extends Component> Stream<T> getComponents(Class<T> type) {
        return GameObject.getComponents(getGameObject(), type);
    }

    /**
     * Adds the specified component to the same game object associated with this
     * component. Similar to calling
     * {@code GameObject.addComponent(getGameObject(), component)}.
     * 
     * <p>
     * {@code component} is started immediately if it is a {@link Behaviour} and the
     * game object associated with this component is already started.
     * 
     * @param <T>       the type of {@link Component} to add.
     * @param component the component to add to {@code gameObject}.
     * @return the passed in {@code component}.
     * @throws NotAGameObjectException if the game object associated with this
     *                                 component is {@code null} or is not a valid
     *                                 game object created with
     *                                 {@link GameObject#create}.
     * @see GameObject#addComponent(Group, Component)
     */
    public <T extends Component> T addComponent(T component) {
        return GameObject.addComponent(getGameObject(), component);
    }

    /*
     * Transforms
     */

    /**
     * Returns the x position transform property of the same game object associated
     * with this component for getting and setting its value. Similar to calling
     * {@code GameObject.posX(getGameObject())}.
     * 
     * @return the x position transform property of the same game object associated
     *         with this component.
     * @throws NullPointerException if the game object associated with this
     *                              component is {@code null}.
     * @see GameObject#posX(Group)
     * @see Group#translateXProperty()
     */
    public DoubleProperty posX() {
        return GameObject.posX(getGameObject());
    }

    /**
     * Returns the y position transform property of the same game object associated
     * with this component for getting and setting its value. Similar to calling
     * {@code GameObject.posY(getGameObject())}.
     * 
     * @return the y position transform property of the same game object associated
     *         with this component.
     * @throws NullPointerException if the game object associated with this
     *                              component is {@code null}.
     * @see GameObject#posY(Group)
     * @see Group#translateYProperty()
     */
    public DoubleProperty posY() {
        return GameObject.posY(getGameObject());
    }

    /**
     * Returns the rotation transform property of the same game object associated
     * with this component for getting and setting its value. Similar to calling
     * {@code GameObject.rotate(getGameObject())}.
     * 
     * @return the rotation transform property of the same game object associated
     *         with this component.
     * @throws NullPointerException if the game object associated with this
     *                              component is {@code null}.
     * @see GameObject#rotate(Group)
     * @see Group#rotateProperty()
     */
    public DoubleProperty rotate() {
        return GameObject.rotate(getGameObject());
    }

    /**
     * Returns the x scale transform property of the same game object associated
     * with this component for getting and setting its value. Similar to calling
     * {@code GameObject.scaleX(getGameObject())}.
     * 
     * @return the x scale transform property of the same game object associated
     *         with this component.
     * @throws NullPointerException if the game object associated with this
     *                              component is {@code null}.
     * @see GameObject#scaleX(Group)
     * @see Group#scaleXProperty()
     */
    public DoubleProperty scaleX() {
        return GameObject.scaleX(getGameObject());
    }

    /**
     * Returns the y scale transform property of the same game object associated
     * with this component for getting and setting its value. Similar to calling
     * {@code GameObject.scaleY(getGameObject())}.
     * 
     * @return the y scale transform property of the same game object associated
     *         with this component.
     * @throws NullPointerException if the game object associated with this
     *                              component is {@code null}.
     * @see GameObject#scaleY(Group)
     * @see Group#scaleYProperty()
     */
    public DoubleProperty scaleY() {
        return GameObject.scaleY(getGameObject());
    }

}
