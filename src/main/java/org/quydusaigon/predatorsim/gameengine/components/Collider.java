package org.quydusaigon.predatorsim.gameengine.components;

import javafx.scene.Node;
import org.quydusaigon.predatorsim.gameengine.Component;

import javafx.geometry.Bounds;
import org.quydusaigon.predatorsim.gameengine.NodeComponent;

public abstract class Collider<T extends Node> {

    private final NodeComponent<T> nodeComponent;

    public Collider(NodeComponent<T> nodeComponent) {
        this.nodeComponent = nodeComponent;
    }

    public boolean collides(Collider collider){
        /*TO DO*/
        return false;
    }

    public NodeComponent<T> getNodeComponent(){
        /*TO DO*/
        return null;
    }

    public Bounds getBounds(){
        /*TO DO*/
        return null;
    }

    /*    public boolean collides(Collider other) {
        return getBounds().intersects(other.getBounds());
    }

    protected abstract Bounds getBounds();*/

}
