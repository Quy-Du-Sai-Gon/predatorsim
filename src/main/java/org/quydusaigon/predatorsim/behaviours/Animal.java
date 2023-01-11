package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.util.AnimalStat;
import org.quydusaigon.predatorsim.util.StateConstructor;

public abstract class Animal {

    protected StateConstructor stateConstructor;
    public AnimalStat animalStat;

    public Animal(AnimalStat animalStat) {
        this.animalStat = animalStat;
    }

}
