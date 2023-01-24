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

    @Override
    public String toString() {
        return String.format("%sHunger Resilience: %d\nGroup radius: %.2f\n",
                super.toString(), starvationResilience, groupRadius);
    }
}
