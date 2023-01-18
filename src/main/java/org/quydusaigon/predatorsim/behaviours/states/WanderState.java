package org.quydusaigon.predatorsim.behaviours.states;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.WanderBehaviour;

public class WanderState extends State {
    WanderBehaviour wanderBehaviour;
    private boolean foundObject = false;

    public WanderState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {
        wanderBehaviour = animalSM.getWanderBehaviour();
        wanderBehaviour.setSeed();
    }

    @Override
    public void update() {
        if(foundObject){
            animalSM.changeState(animalSM.getStateConstructor().getSurvivalState());
            foundObject = false;
            return;
        }

        wanderBehaviour.doAction();
    }

    @Override
    public void exit() {

    }

    public void setFoundObject(boolean found) {
        foundObject = found;
    }
}
