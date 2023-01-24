package org.quydusaigon.predatorsim.gameengine.util;

import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

import javafx.scene.Group;

/**
 * An interface containing a single method for instantiating a new game object
 * in the scene. Instances of {@code Prefab} should implement
 * {@link #instantiate} as a wrapper of {@link GameObject#create} to create new
 * game objects and return them.
 * 
 * @see GameObject
 */
public interface Prefab {

    /**
     * Creates and returns a new game object.
     *
     * <p>
     * This is a factory method for creating new prefabricated game objects using
     * the same code. This should be a wrapper of the {@link GameObject#create}
     * method.
     * 
     * @param tf     the initial transform of the new game object.
     * @param parent the parent game object to attach the new one to.
     * @return a new game object.
     * @see GameObject
     * @see GameObject#create
     * @see TransformInit
     */
    public Group instantiate(TransformInit tf, Group parent);
}
