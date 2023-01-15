package org.quydusaigon.predatorsim.gameengine.util;

import java.util.Random;

public class TransformInit {
    public final double posX;
    public final double posY;
    public final double rotate;
    public final double scaleX;
    public final double scaleY;

    public static final TransformInit DEFAULT = new TransformInit(0, 0);

    public TransformInit(double posX, double posY, double rotate, double scaleX, double scaleY) {
        this.posX = posX;
        this.posY = posY;
        this.rotate = rotate;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public TransformInit(double posX, double posY, double rotate) {
        this(posX, posY, rotate, 1, 1);
    }

    public TransformInit(double posX, double posY) {
        this(posX, posY, 0);
    }

    public static TransformInit getRandomTransformInit() {        
        return new TransformInit(Math.random() * 1000, Math.random() * 800);
    }
}
