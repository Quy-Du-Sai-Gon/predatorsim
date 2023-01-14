package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

public abstract class HowlBehaviour extends AnimalBehaviour{
    @Override
    void doAction(){
        doHowl();
    }
    public abstract void doHowl();
}
