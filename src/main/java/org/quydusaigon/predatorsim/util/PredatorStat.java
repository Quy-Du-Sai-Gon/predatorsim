package org.quydusaigon.predatorsim.util;

public class PredatorStat extends AnimalStat {

    public float starvationResilience;
    public double groupRadius;
    public  double howlingRadius;

    public PredatorStat(double runSpeed, double visionRange, float starvationResilience,
            double groupRadius, double howlingRadius) {
        super(runSpeed, visionRange);
        this.starvationResilience = starvationResilience;
        this.groupRadius = groupRadius;
        this.howlingRadius = howlingRadius;
    }

    @Override
    public String toString() {
        return String.format("%sHunger Resilience: %f\nGroup radius: %.2f\n",
                super.toString(), starvationResilience, groupRadius);
    }
}
