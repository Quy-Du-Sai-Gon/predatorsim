package org.quydusaigon.predatorsim;

import javafx.geometry.Point2D;

public class AnimalStat {
    public double runSpeed;
    public double visionRange;
    public Point2D position2D;

    public AnimalStat(double runSpeed, double visionRange) {
        this.runSpeed = runSpeed;
        this.visionRange = visionRange;
    }
}
