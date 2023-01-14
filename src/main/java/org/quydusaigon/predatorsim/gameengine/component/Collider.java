package org.quydusaigon.predatorsim.gameengine.component;

import javafx.scene.Node;
import javafx.scene.shape.Shape;
import javafx.geometry.Bounds;

public class Collider<T extends Node> extends Component {

    private final NodeComponent<T> nodeComponent;

    public Collider(NodeComponent<T> nodeComponent) {
        this.nodeComponent = nodeComponent;
        nodeComponent.setCollider(this);
    }

    /*
     * NodeComponent access
     */

    public NodeComponent<T> getNodeComponent() {
        return nodeComponent;
    }

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

    public boolean collides(Bounds globalBounds) {
        var node = getNode();
        return node.intersects(node.sceneToLocal(globalBounds));
    }

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

    public boolean collides(Collider<?> other) {
        return collides(other.getNode());
    }

    private static Bounds getBoundsLocalTo(Node node1, Node node2) {
        return node2.sceneToLocal(node1.localToScene(node1.getBoundsInLocal()));
    }

}
