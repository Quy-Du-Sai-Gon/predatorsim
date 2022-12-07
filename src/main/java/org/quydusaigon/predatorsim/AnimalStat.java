package org.quydusaigon.predatorsim;

public class AnimalStat {
    public double runSpeed;
    public double visionRange;
    public Position2d position2D;

    public AnimalStat(double runSpeed, double visionRange)
    {
        this.runSpeed = runSpeed;
        this.visionRange = visionRange;
    }
}
