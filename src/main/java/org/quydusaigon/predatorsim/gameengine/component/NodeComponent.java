package org.quydusaigon.predatorsim.gameengine.component;

import javafx.scene.Node;

public class NodeComponent<T extends Node> extends Component {
    private final T node;
    private Collider<T> collider;

    public NodeComponent(T node) {
        this.node = node;
    }

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
