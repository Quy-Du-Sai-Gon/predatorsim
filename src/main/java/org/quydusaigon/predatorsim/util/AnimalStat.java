package org.quydusaigon.predatorsim.util;

public class AnimalStat {

    public double runSpeed;
    public double visionRange;

    public AnimalStat(double runSpeed, double visionRange) {
        this.runSpeed = runSpeed;
        this.visionRange = visionRange;
    }

    @Override
    public String toString() {
        return String.format("Speed: %.3f\nVision range: %.3f\n", runSpeed, visionRange);
    }
}
