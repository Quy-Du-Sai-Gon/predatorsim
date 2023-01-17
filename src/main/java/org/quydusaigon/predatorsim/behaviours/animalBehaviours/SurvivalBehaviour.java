package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

public abstract class SurvivalBehaviour extends AnimalBehaviour {

    @Override
    void doAction() {
        doSurvival();
    }

    public abstract void doSurvival();
}
