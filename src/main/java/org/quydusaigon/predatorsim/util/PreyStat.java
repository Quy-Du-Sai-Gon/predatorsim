package org.quydusaigon.predatorsim.util;

public class PreyStat extends AnimalStat {

    public PreySize size;
    public Double nutrition;
    public double defense;

    public PreyStat(double runSpeed, double visionRange, PreySize size, double nutrition, double defense) {
        super(runSpeed, visionRange);
        this.size = size;
        this.nutrition = nutrition;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return String.format("%sNutrition: %d\nDefense: %.3f\n", super.toString(), nutrition, defense);
    }
}
