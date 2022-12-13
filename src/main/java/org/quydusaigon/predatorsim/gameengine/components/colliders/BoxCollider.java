package org.quydusaigon.predatorsim.gameengine.components.colliders;

import org.quydusaigon.predatorsim.gameengine.components.Collider;

import javafx.geometry.Rectangle2D;

public class BoxCollider extends Collider {

    private Rectangle2D box;

    public BoxCollider(double x, double y, double w, double h) {
        box = new Rectangle2D(x, y, w, h);
    }

    public double getX() {
        return box.getMinX();
    }

    public double getY() {
        return box.getMinY();
    }

    public double getWidth() {
        return box.getWidth();
    }

    public double getHeight() {
        return box.getHeight();
    }
}
