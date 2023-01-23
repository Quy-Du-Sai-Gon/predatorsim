package org.quydusaigon.predatorsim.gameengine.component;

/**
 * A {@link Component} containing methods that can be overridden by subclasses
 * to enable certain behaviours during runtime. Can be considered as a scripting
 * component.
 * 
 * @see Component
 */
public abstract class Behaviour extends Component {

    /**
     * This method is called once when the component is starting.
     *
     * <p>
     * Can be overridden by subclasses of {@link Behaviour}.
     * 
     * @see #update()
     */
    public void start() {
    }

    /**
     * This method is called every frame for handling updates.
     *
     * <p>
     * Can be overridden by subclasses of {@link Behaviour}.
     * 
     * @see #start()
     */
    public void update() {
    }

    /**
     * This method is called once during the frame where a {@link Collider} existing
     * on the same game object as this {@link Behaviour} begins colliding with
     * another {@link Collider}.
     *
     * <p>
     * The information of the two colliders are passed to the method.
     *
     * <p>
     * Can be overridden by subclasses of {@link Behaviour}.
     * 
     * @param collider a collider existing on the same game object as this
     *                 {@link Behaviour}.
     * @param other    any other collider that {@code collider} has the collision
     *                 event with.
     * @see Collider
     * @see #onCollisionStay(Collider, Collider)
     * @see #onCollisionExit(Collider, Collider)
     */
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
    }

    /**
     * This method is called every frame a {@link Collider} existing on the same
     * game object as this {@link Behaviour} keeps colliding with another
     * {@link Collider}. This is called the frame after the frame where
     * {@link #onCollisionEnter(Collider, Collider) onCollisionEnter} happens and
     * before the frame where {@link #onCollisionExit(Collider, Collider)
     * onCollisionExit} happens.
     *
     * <p>
     * The information of the two colliders are passed to the method.
     *
     * <p>
     * Can be overridden by subclasses of {@link Behaviour}.
     * 
     * @param collider a collider existing on the same game object as this
     *                 {@link Behaviour}.
     * @param other    any other collider that {@code collider} has the collision
     *                 event with.
     * @see Collider
     * @see #onCollisionEnter(Collider, Collider)
     * @see #onCollisionExit(Collider, Collider)
     */
    public void onCollisionStay(Collider<?> collider, Collider<?> other) {
    }

    /**
     * This method is called once during the frame where a {@link Collider} existing
     * on the same game object as this {@link Behaviour} was colliding with another
     * {@link Collider} the frame before but not anymore in this frame.
     *
     * <p>
     * The information of the two colliders are passed to the method.
     *
     * <p>
     * Can be overridden by subclasses of {@link Behaviour}.
     * 
     * @param collider a collider existing on the same game object as this
     *                 {@link Behaviour}.
     * @param other    any other collider that {@code collider} has the collision
     *                 event with.
     * @see Collider
     * @see #onCollisionEnter(Collider, Collider)
     * @see #onCollisionStay(Collider, Collider)
     */
    public void onCollisionExit(Collider<?> collider, Collider<?> other) {
    }
}
