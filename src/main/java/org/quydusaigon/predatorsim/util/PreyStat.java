package org.quydusaigon.predatorsim.util;

public class PreyStat extends AnimalStat {
    public PreySize size;
    public double nutrition;

    public PreyStat(double runSpeed, double visionRange, PreySize size, double nutrition){
        super(runSpeed, visionRange);
        this.size = size;
        this.nutrition = nutrition;
    }
}
