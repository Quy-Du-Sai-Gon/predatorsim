package org.quydusaigon.predatorsim.behaviours.util;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.util.Map;

import javafx.geometry.Point2D;

public class Velocity extends Behaviour {

    private double desiredX = 0;
    private double desiredY = 0;

    private double actualX = 0;
    private double actualY = 0;

    @Override
    public void update() {
        var baseX = posX();
        var newX = Map.checkBoundX(baseX.get() + desiredX);
        actualX = newX - baseX.get();
        baseX.set(newX);

        var baseY = posY();
        var newY = Map.checkBoundY(baseY.get() + desiredY);
        actualY = newY - baseY.get();
        baseY.set(newY);
    }

    public void set(double desiredX, double desiredY) {
        this.desiredX = desiredX;
        this.desiredY = desiredY;
    }

    public void set(Point2D desiredVelocity) {
        set(desiredVelocity.getX(), desiredVelocity.getY());
    }

    public double getDesiredX() {
        return desiredX;
    }

    public double getDesiredY() {
        return desiredY;
    }

    public double getActualX() {
        return actualX;
    }

    public double getActualY() {
        return actualY;
    }

    @Override
    public String toString() {
        return String.format("Velocity(%f, %f)", getActualX(), getActualY());
    }
}
