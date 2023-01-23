package org.quydusaigon.predatorsim.util;

public class PredatorStat extends AnimalStat {

    public int starvationResilience;
    public double groupRadius;

    public PredatorStat(double runSpeed, double visionRange, int starvationResilience,
            double groupRadius) {
        super(runSpeed, visionRange);
        this.starvationResilience = starvationResilience;
        this.groupRadius = groupRadius;
    }
}
