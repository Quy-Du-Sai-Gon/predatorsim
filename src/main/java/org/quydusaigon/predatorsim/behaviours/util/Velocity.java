package org.quydusaigon.predatorsim.behaviours.util;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.util.Map;

import javafx.geometry.Point2D;

/**
 * Handles the logic to set a constant velocity for a game object.
 */
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

    /**
     * Sets the desired constant velocity to be applied on the game object.
     * 
     * @param desiredX the desired constant x velocity to be applied on the game
     *                 object.
     * @param desiredY the desired constant y velocity to be applied on the game
     *                 object.
     */
    public void set(double desiredX, double desiredY) {
        this.desiredX = desiredX;
        this.desiredY = desiredY;
    }

    /**
     * Sets the desired constant velocity to be applied on the game object.
     * 
     * @param desiredVelocity the desired constant velocity to be applied on the
     *                        game object.
     */
    public void set(Point2D desiredVelocity) {
        set(desiredVelocity.getX(), desiredVelocity.getY());
    }

    /**
     * Returns the desired x velocity that is set.
     * 
     * @return the desired x velocity that is set.
     */
    public double getDesiredX() {
        return desiredX;
    }

    /**
     * Returns the desired y velocity that is set.
     * 
     * @return the desired y velocity that is set.
     */
    public double getDesiredY() {
        return desiredY;
    }

    /**
     * Returns the actual x velocity that is applied. This may be different from
     * {@link #getDesiredX()} due to obstacles blocking the path.
     * 
     * @return the actual x velocity that is applied.
     */
    public double getActualX() {
        return actualX;
    }

    /**
     * Returns the actual y velocity that is applied. This may be different from
     * {@link #getDesiredY()} due to obstacles blocking the path.
     * 
     * @return the actual y velocity that is applied.
     */
    public double getActualY() {
        return actualY;
    }

    @Override
    public String toString() {
        return String.format("Velocity(%f, %f)", getActualX(), getActualY());
    }
}
