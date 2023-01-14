package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

public abstract class DeadBehaviour extends AnimalBehaviour{
    @Override
    void doAction(){
        doDead();
    }
    public abstract void doDead();
}
