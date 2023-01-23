package org.quydusaigon.predatorsim.gameengine.component;

import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

import javafx.scene.Node;

/**
 * A {@link Component} that wraps around a {@link javafx.scene.Node} in the
 * scene. The {@link Node} this wraps around is automatically added as a child
 * of the game object (represented by a {@link Group}) that this component is
 * added to.
 *
 * <p>
 * Can be considered as a <i>visual component</i> for the scene.
 * 
 * @see Component
 * @see Node
 * @see GameObject
 */
public class NodeComponent<T extends Node> extends Component {
    private final T node;
    private Collider<T> collider;

    /**
     * Creates a new {@link NodeComponent} that wraps around a {@link Node}. The
     * {@link Node} this wraps around is automatically added as a child of the game
     * object (represented by a {@link Group}) that this component is added to.
     * 
     * @param node the {@link Node} to wrap.
     * @see Node
     * @see GameObject
     */
    public NodeComponent(T node) {
        if (node == null)
            throw new NullPointerException("Node cannot be null");
        this.node = node;
    }

    /**
     * Returns the {@link javafx.scene.Node} that this {@link NodeComponent} wraps.
     * 
     * @return the {@link javafx.scene.Node} that this {@link NodeComponent} wraps.
     * @see Node
     */
    public T getNode() {
        return node;
    }

    /*
     * Deletion
     */

    @Override
    public void onDestroy() {
        destroy(collider);
    }

    /*
     * Collider access
     */

    Collider<T> getCollider() {
        return this.collider;
    }

    void setCollider(Collider<T> collider) {
        if (this.collider != null && collider != null) {
            throw new IllegalArgumentException("Attempt to set a collider for NodeComponent when one already exists");
        }
        this.collider = collider;
    }

}
