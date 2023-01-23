package org.quydusaigon.predatorsim.gameengine.component;

import javafx.scene.Node;
import javafx.scene.shape.Shape;
import javafx.geometry.Bounds;

/**
 * A {@link Component} acting as a physical node in the scene that can collides
 * with other {@code Collider}s. This class wraps around a
 * {@link NodeComponent}, enabling collision detection for certain {@link Node}s
 * in the scene.
 * 
 * @see Component
 * @see NodeComponent
 */
public class Collider<T extends Node> extends Component {

    private final NodeComponent<T> nodeComponent;

    /**
     * Creates a new collider that wraps around {@code nodeComponent}, enabling
     * collision detection for that {@link NodeComponent}.
     * 
     * @param nodeComponent the {@link NodeComponent} to wrap and enable collision
     *                      for.
     * @see NodeComponent
     */
    public Collider(NodeComponent<T> nodeComponent) {
        this.nodeComponent = nodeComponent;
        nodeComponent.setCollider(this);
    }

    /*
     * NodeComponent access
     */

    /**
     * Returns the {@link NodeComponent} that this collider wraps.
     * 
     * @return the {@link NodeComponent} that this collider wraps.
     * @see NodeComponent
     */
    public NodeComponent<T> getNodeComponent() {
        return nodeComponent;
    }

    /**
     * Returns the {@link javafx.scene.Node} wrapped by the {@link NodeComponent}
     * that this collider wraps. Short-hand for
     * {@code getNodeComponent().getNode()}.
     * 
     * @return the {@link javafx.scene.Node} wrapped by the {@link NodeComponent}
     *         that this collider wraps.
     * @see NodeComponent
     * @see Node
     */
    public T getNode() {
        return getNodeComponent().getNode();
    }

    @Override
    public void onDestroy() {
        getNodeComponent().setCollider(null);
    }

    /*
     * Collision detection
     */

    /**
     * Checks whether this collider collides with a global {@link Bounds}.
     * Essentially checking whether the underlying {@link Node} of this collider
     * intersects with {@code globalBounds}.
     * 
     * @param globalBounds the {@link Bounds} to check for.
     * @return whether this collider collides with a global {@link Bounds}.
     * @see Node
     * @see Bounds
     */
    public boolean collides(Bounds globalBounds) {
        var node = getNode();
        return node.intersects(node.sceneToLocal(globalBounds));
    }

    /**
     * Checks whether this collider collides with a {@link Node}. Essentially
     * checking whether the underlying {@link Node} of this collider intersects with
     * {@code node}.
     * 
     * @param node the {@link Node} to check for.
     * @return whether this collider collides with a {@link Node}.
     * @see Node
     */
    public boolean collides(Node node) {
        var thisNode = getNode();
        if (thisNode instanceof Shape && node instanceof Shape) {
            // 2 Shapes collide if their intersection is not empty
            // (its Bounds' width is not -1)
            // NOTE: Shape.intersect considers the global transforms of both shapes
            return Shape.intersect((Shape) thisNode, (Shape) node)
                    .getBoundsInLocal().getWidth() != -1;
        }
        if (!(thisNode instanceof Shape)) {
            return node.intersects(getBoundsLocalTo(thisNode, node));
        }
        return thisNode.intersects(getBoundsLocalTo(node, thisNode));
    }

    /**
     * Checks whether this collider collides with another collider. Essentially
     * checking whether the underlying {@link Node} of this collider intersects with
     * the underlying {@link Node} of the {@code other} collider.
     * 
     * @param other the other collider to check for.
     * @return whether this collider collides with another collider.
     * @see Node
     */
    public boolean collides(Collider<?> other) {
        return collides(other.getNode());
    }

    private static Bounds getBoundsLocalTo(Node node1, Node node2) {
        return node2.sceneToLocal(node1.localToScene(node1.getBoundsInLocal()));
    }

}
