package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import org.quydusaigon.predatorsim.behaviours.animalBehaviours.AnimalBehaviour;

public abstract class WanderBehaviour extends AnimalBehaviour {
    public abstract void doWander();
    @Override
    void doAction(){
        doWander();
    }
}
