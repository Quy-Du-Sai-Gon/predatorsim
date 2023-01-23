package org.quydusaigon.predatorsim.util;

public class PreyStat extends AnimalStat {

    public PreySize size;
    public int nutrition;
    public double defense;

    public PreyStat(double runSpeed, double visionRange, PreySize size, int nutrition, double defense){
        super(runSpeed, visionRange);
        this.size = size;
        this.nutrition = nutrition;
        this.defense = defense;
    }
}
