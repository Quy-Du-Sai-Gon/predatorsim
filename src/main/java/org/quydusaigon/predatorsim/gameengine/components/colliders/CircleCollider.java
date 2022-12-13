package org.quydusaigon.predatorsim.gameengine.components.colliders;

import org.quydusaigon.predatorsim.gameengine.components.Collider;

import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

public class CircleCollider extends Collider {

    private Circle circle;

    public CircleCollider(double x, double y, double radius) {
        circle = new Circle(x, y, radius);
    }

    public double getX() {
        return circle.getCenterX();
    }

    public double getY() {
        return circle.getCenterY();
    }

    public double getRadius() {
        return circle.getRadius();
    }

    @Override
    protected Bounds getBounds() {
        return circle.getBoundsInParent();
    }
}
