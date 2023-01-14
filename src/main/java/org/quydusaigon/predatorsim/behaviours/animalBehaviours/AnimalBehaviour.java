package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.util.AnimalStat;

public abstract class AnimalBehaviour extends Behaviour {
    protected AnimalStat animalStat;
    public void setAnimalStat(AnimalStat animalStat){
        this.animalStat = animalStat;
    }
    abstract void doAction();
}
