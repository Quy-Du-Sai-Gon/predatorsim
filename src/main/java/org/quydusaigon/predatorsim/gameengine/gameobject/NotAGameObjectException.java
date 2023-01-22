package org.quydusaigon.predatorsim.gameengine.gameobject;

/**
 * A runtime exception thrown when {@link GameObject} operations are called on
 * invalid game objects ({@link javafx.scene.Group}s that are not created with
 * {@link GameObject#create}).
 * 
 * @see GameObject
 */
public class NotAGameObjectException extends RuntimeException {

}
