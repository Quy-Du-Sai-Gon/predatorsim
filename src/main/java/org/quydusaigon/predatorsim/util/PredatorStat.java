package org.quydusaigon.predatorsim.util;

public class PredatorStat extends AnimalStat {
    public double starvationResilience;
    public double defenceChance;
    public double groupRadius;
    public PredatorStat(double runSpeed, double visionRange, double starvationResilience, double defenceChance, double groupRadius){
        super(runSpeed, visionRange);
        this.starvationResilience = starvationResilience;
        this.defenceChance = defenceChance;
        this.groupRadius = groupRadius;
    }
}
