package org.quydusaigon.predatorsim.gameengine;

import javafx.scene.Node;

public class NodeComponent<T extends Node> extends Component {
    private final T node;

    public NodeComponent(T node) {
        this.node = node;
    }

    public T getNode(){
        /*TO DO*/
        return null;
    }
}
