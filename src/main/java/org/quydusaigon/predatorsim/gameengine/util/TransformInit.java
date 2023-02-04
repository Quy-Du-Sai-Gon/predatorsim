package org.quydusaigon.predatorsim.gameengine.util;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Parameter;

/**
 * This class is for creating object that stores data for initializing the
 * transform of a new game object.
 * 
 * @see GameObject
 */
public class TransformInit {
    /**
     * The x position of the transform.
     */
    public final double posX;
    /**
     * The y position of the transform.
     */
    public final double posY;
    /**
     * The rotation of the transform.
     */
    public final double rotate;
    /**
     * The scale on the x axis of the transform.
     */
    public final double scaleX;
    /**
     * The scale on the y axis of the transform.
     */
    public final double scaleY;

    /**
     * The default initial transform with position (0, 0), rotation 0, and scale (1,
     * 1).
     */
    public static final TransformInit DEFAULT = new TransformInit(0, 0);

    /**
     * Creates new transform data.
     * 
     * @param posX   the x position of the transform.
     * @param posY   the y position of the transform.
     * @param rotate the rotation of the transform.
     * @param scaleX the scale on the x axis of the transform.
     * @param scaleY the scale on the y axis of the transform.
     */
    public TransformInit(double posX, double posY, double rotate, double scaleX, double scaleY) {
        this.posX = posX;
        this.posY = posY;
        this.rotate = rotate;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * Creates new transform data with default scale of (1, 1).
     * 
     * @param posX   the x position of the transform.
     * @param posY   the y position of the transform.
     * @param rotate the rotation of the transform.
     */
    public TransformInit(double posX, double posY, double rotate) {
        this(posX, posY, rotate, 1, 1);
    }

    /**
     * Creates new transform data with default rotation of 0 and default scale of
     * (1, 1).
     * 
     * @param posX the x position of the transform.
     * @param posY the y position of the transform.
     */
    public TransformInit(double posX, double posY) {
        this(posX, posY, 0);
    }

    /**
     * Returns a new {@code TransformInit} with random data with respect to
     * {@link App} window's size.
     * 
     * @return a new {@code TransformInit} with random data with respect to
     *         {@link App} window's size.
     */
    public static TransformInit getRandomTransformInit() {
        return new TransformInit(Math.random() * Parameter.getWindowWidth(), Math.random() * Parameter.getWindowHeight());
    }
}
