package org.quydusaigon.predatorsim.gameengine.event;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;

/**
 * This interface represents a collision event that is handled by one of the
 * methods of a {@link Behaviour}.
 */
public interface CollisionEvent {

    /**
     * Raises a certain collision event on {@code behaviour} with {@code collider}
     * and {@code other} as arguments.
     * 
     * @param behaviour the {@link Behaviour} whose collision event handler is to be
     *                  called.
     * @param collider  the collider to pass to the event handler.
     * @param other     the other collider to pass to the even handler.
     * @see Behaviour
     * @see Collider
     */
    public void raise(Behaviour behaviour, Collider<?> collider, Collider<?> other);
}
