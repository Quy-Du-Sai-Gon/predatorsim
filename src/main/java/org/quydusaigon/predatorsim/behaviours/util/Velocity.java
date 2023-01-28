package org.quydusaigon.predatorsim.behaviours.util;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;

import javafx.geometry.Point2D;

public class Velocity extends Behaviour {

    private double x = 0;
    private double y = 0;

    @Override
    public void update() {
        var baseX = posX();
        baseX.set(baseX.get() + x);

        var baseY = posY();
        baseY.set(baseY.get() + y);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Point2D p) {
        set(p.getX(), p.getY());
    }

    @Override
    public String toString() {
        return String.format("Velocity(%f, %f)", x, y);
    }
}
