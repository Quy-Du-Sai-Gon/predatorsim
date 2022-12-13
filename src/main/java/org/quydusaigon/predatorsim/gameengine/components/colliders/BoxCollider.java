package org.quydusaigon.predatorsim.gameengine.components.colliders;

import org.quydusaigon.predatorsim.gameengine.components.Collider;

import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

public class BoxCollider extends Collider {

    private Rectangle box;

    public BoxCollider(double x, double y, double w, double h) {
        box = new Rectangle(x, y, w, h);
    }

    public double getX() {
        return box.getX();
    }

    public double getY() {
        return box.getY();
    }

    public double getWidth() {
        return box.getWidth();
    }

    public double getHeight() {
        return box.getHeight();
    }

    @Override
    protected Bounds getBounds() {
        return box.getBoundsInParent();
    }
}
