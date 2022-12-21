package org.quydusaigon.predatorsim.gameengine.component;

import javafx.scene.Node;
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

    @Override
    public void onDestroy() {
        getNodeComponent().setCollider(null);
    }

    /*
     * Collision detection
     */

    public boolean collides(Bounds bound) {
        return getBounds().intersects(bound);
    }

    public boolean collides(Collider<?> other) {
        return collides(other.getBounds());
    }

    public Bounds getBounds() {
        return getNodeComponent().getNode().getBoundsInParent();
    }

}
