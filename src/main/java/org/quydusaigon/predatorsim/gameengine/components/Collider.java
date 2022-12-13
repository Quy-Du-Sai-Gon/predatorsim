package org.quydusaigon.predatorsim.gameengine.components;

import org.quydusaigon.predatorsim.gameengine.Component;

import javafx.geometry.Bounds;

public abstract class Collider extends Component {

    public boolean collides(Collider other) {
        return getBounds().intersects(other.getBounds());
    }

    protected abstract Bounds getBounds();

}
